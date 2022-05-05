package com.curso.springboot.serviceimpl;

import com.curso.springboot.entities.Client;
import com.curso.springboot.repository.ClientRepository;
import com.curso.springboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ClienteServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public Flux<Client> getAll() {
        return (Flux<Client>) clientRepository.findAll();
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
        return clientRepository.insert(cliente);
    }

    @Override
    public Mono<Client> update(Client cliente) {
        return clientRepository.save(cliente);
    }

}
