package com.curso.springboot.serviceimpl;

import com.curso.springboot.entities.Accounts;
import com.curso.springboot.entities.Client;
import com.curso.springboot.entities.ClientProducts;
import com.curso.springboot.entities.Credits;
import com.curso.springboot.repository.ClientRepository;
import com.curso.springboot.services.IClientService;
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
        return clientRepository.findAll().doOnComplete(() -> log.info("Retrieving all Accounts"));
    }

    @Override

    public Mono<Client> getById(String id) {
        return clientRepository.findById(id);
    }

    @Override
    public Mono<Void> delete(String id) {
        return clientRepository.deleteById(id);
    }

    @Override
    public Mono<Client> save(Client cliente) {
        return clientRepository.insert(cliente)
                .doOnSuccess(ex -> log.info("Create new client with id: {}", cliente.getId()));
    }

    @Override
    public Mono<Client> update(Client cliente) {
        return clientRepository.save(cliente);
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
