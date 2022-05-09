package com.curso.springboot.controllers;

import com.curso.springboot.entities.Client;
import com.curso.springboot.entities.ClientProducts;
import com.curso.springboot.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @GetMapping("/api/clients")
    public Flux<Client> getClientAll() {
        return iClientService.getAll();
    }

    @GetMapping("/api/clients/{id}")
    public Mono<Client> getClientById(@PathVariable String id) {
        return iClientService.getById(id);
    }

    @DeleteMapping("/api/clients/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
        return iClientService.getById(id)
                .flatMap(c -> iClientService.delete(c.getId()));
    }

    @PostMapping("/api/clients")
    public Mono<Client> saveCliente(@RequestBody Client cliente) {
        return iClientService.save(cliente);
    }

    @PutMapping("/api/clients/{id}/clients")
    public Mono<Client> updateCliente(@RequestBody Client cliente) {
        return iClientService.getById(cliente.getId())
                .flatMap(c -> iClientService.update(c));
    }

    @GetMapping("/api/clients/{id}/products")
    public Mono<ClientProducts> getProductsByClient(@PathVariable String id) {
        return iClientService.getProductsByClient(id);
    }
}
