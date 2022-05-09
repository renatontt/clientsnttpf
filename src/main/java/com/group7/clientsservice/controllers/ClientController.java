package com.group7.clientsservice.controllers;

import com.group7.clientsservice.entities.Client;
import com.group7.clientsservice.entities.ClientProducts;
import com.group7.clientsservice.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    private IClientService iClientService;

    @GetMapping
    public Flux<Client> getClientAll() {
        return iClientService.getAll();
    }

    @GetMapping("/{id}")
    public Mono<Client> getClientById(@PathVariable String id) {
        return iClientService.getById(id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteClient(@PathVariable String id) {
        return iClientService.delete(id);
    }

    @PostMapping
    public Mono<Client> saveCliente(@RequestBody Client cliente) {
        return iClientService.save(cliente);
    }

    @PutMapping("")
    public Mono<Client> updateCliente(@RequestBody Client cliente) {
        return iClientService.update(cliente);
    }

    @GetMapping("/{id}/products")
    public Mono<ClientProducts> getProductsByClient(@PathVariable String id) {
        return iClientService.getProductsByClient(id);
    }
}
