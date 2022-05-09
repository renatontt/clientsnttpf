package com.curso.springboot.controllers;

import com.curso.springboot.entities.Client;
import com.curso.springboot.entities.ClientProducts;
import com.curso.springboot.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
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
        return iClientService.getById(id)
                .flatMap(c -> iClientService.delete(c.getId()));
    }

    @PostMapping
    public Mono<Client> saveCliente(@RequestBody Client cliente) {
        return iClientService.save(cliente);
    }

    @PutMapping("/{id}")
    public Mono<Client> updateCliente(@PathVariable String id, @RequestBody Client cliente) {
        cliente.setId(id);
        return iClientService.getById(id)
                .flatMap(c -> iClientService.update(cliente));
    }

    @GetMapping("/{id}/products")
    public Mono<ClientProducts> getProductsByClient(@PathVariable String id) {
        return iClientService.getProductsByClient(id);
    }
}
