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

    @GetMapping("/api/clientes")
    public Flux<Client> getClientAll() {
        return clientService.getAll();
    }

    @GetMapping("/api/clientes/{id}")
    public Mono<Client> getClientById(@PathVariable String id) {
        return clientService.getById(id);
    }

    @DeleteMapping("/api/clientes/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
        return clientService.delete(id);
    }

    @PostMapping("/api/clientes")
    public Mono<Client> saveCliente(@RequestBody Client cliente) {
        return clientService.save(cliente);
    }

    @PutMapping("/api/clientes")
    public Mono<Client> updateCliente(@RequestBody Client cliente) {
        return clientService.update(cliente);
    }
}
