package com.group7.clientsservice.serviceimpl;

import com.group7.clientsservice.entities.Accounts;
import com.group7.clientsservice.entities.Client;
import com.group7.clientsservice.entities.ClientProducts;
import com.group7.clientsservice.entities.Credits;
import com.group7.clientsservice.exception.ClientsCreationException;
import com.group7.clientsservice.exception.ClientsNotFoundException;
import com.group7.clientsservice.repository.ClientRepository;
import com.group7.clientsservice.service.IClientService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ClienteServiceImpl implements IClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> getAll() {
        return clientRepository.findAll().doOnComplete(() -> log.info("Retrieving all Clients"));
    }

    @Override

    public Mono<Client> getById(String id) {
        return clientRepository.findById(id)
                .switchIfEmpty(Mono.error(new ClientsNotFoundException("Clients not found with id: " + id)))
                .doOnError(ex -> log.error("Error find client", ex));
    }

    public Mono<Client> ByDocumentTypeAndDocumentNumber(String documentType, String documentNumber) {
        return clientRepository.findClientByDocumentTypeAndDocumentNumber(documentType, documentNumber);
    }

    @Override
    public Mono<Void> delete(String id) {
        return getById(id)
                .flatMap(c -> clientRepository.delete(c));
    }

    @Override
    public Mono<Client> save(Client cliente) {
        return ByDocumentTypeAndDocumentNumber(cliente.getDocumentType(), cliente.getDocumentNumber())
                .hasElement()
                .flatMap(exists -> {
                    if (exists) return Mono.error(new ClientsCreationException("Client already exists by " +
                            "DocumentType: "+cliente.getDocumentType()+", DocumentNumber: "+ cliente.getDocumentNumber()));
                    return clientRepository.insert(cliente);
                })
                .doOnSuccess(c -> log.info("Created new client with ID: {}", c.getId()))
                .doOnError(ex -> log.error("Error creating new client ", ex));
    }

    @Override
    public Mono<Client> update(Client cliente) {
        return getById(cliente.getId())
                .flatMap(c -> clientRepository.save(c))
                .doOnSuccess(ex -> log.info("Update Client with id: {}", cliente.getId()))
                .doOnError(ex -> log.error("Error update Client ", ex));
    }

    @Override
    public Mono<Boolean> existsById(String id) {
        return clientRepository.existsById(id);
    }

    public Mono<ClientProducts> getProductsByClient(String id) {
        return getById(id)
                .flatMap(c -> {
                    ClientProducts clientProducts = new ClientProducts();
                    clientProducts.setClient(c);
                    return getAccounts(c.getId())
                            .collectList()
                            .flatMap(acc -> {
                                clientProducts.setAccounts(acc);
                                return getCredits(c.getId())
                                        .collectList()
                                        .flatMap(cred -> {
                                            clientProducts.setCredits(cred);
                                            return Mono.just(clientProducts);
                                        });
                            });
                });
    }
    private Flux<Accounts> getAccounts(String id) {
        Flux<Accounts> accounts = WebClient.create()
                .get()
                .uri("http://localhost:8081/accounts/client/{id}", id)
                .retrieve()
                .bodyToFlux(Accounts.class);
        return accounts;
    }

    private Flux<Credits> getCredits(String id) {
        Flux<Credits> credits = WebClient.create()
                .get()
                .uri("http://localhost:8082/api/credits/client/{id}", id)
                .retrieve()
                .bodyToFlux(Credits.class);
        return credits;
    }

}
