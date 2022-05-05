package com.curso.springboot.controllers;

import com.curso.springboot.entities.Client;
import com.curso.springboot.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @GetMapping("/api/clients")
    public Flux<Client> getClientAll() {
        return clientService.getAll();
    }

    @GetMapping("/api/clients/{id}")
    public Mono<Client> getClientById(@PathVariable String id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/api/clients/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
        return clientService.getById(id)
                .flatMap(c -> clientService.delete(c.getId()));
    }

    @PostMapping("/api/clients")
    public Mono<Client> saveCliente(@RequestBody Client cliente) {
        return clientService.save(cliente);
    }

    @PutMapping("/api/clients")
    public Mono<Client> updateCliente(@RequestBody Client cliente) {
        return clientService.getById(cliente.getId())
                .flatMap(c -> clientService.update(c));
    }
}
