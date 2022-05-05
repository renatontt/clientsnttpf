package com.curso.springboot.services;

import com.curso.springboot.entities.Client;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ClientService {
    Flux<Client> getAll();

    Mono<Client> getById(String id);

    Mono<Void> delete(String id);

    Mono<Client> save(Client cliente);

    Mono<Client> update(Client cliente);

    Mono<Boolean> existsById(String id);
}
