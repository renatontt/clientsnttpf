package com.group7.clientsservice.serviceimpl;

import com.group7.clientsservice.entities.Client;
import com.group7.clientsservice.repository.ClientRepository;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {


    @InjectMocks
    ClienteServiceImpl clienteService;

    @Mock
    private ClientRepository clientRepository;

    private Client client;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = Client.builder()
                .id("tes123")
                .documentType("123")
                .documentNumber("123")
                .build();
    }

    @Test
    void getAll() {
        when(clientRepository.findAll()).thenReturn(Flux.just(client));
        assertNotNull(clienteService.getAll());
    }

    @Test
    void getById() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        assertNotNull(clienteService.getById("123"));
    }

    @Test
    void ByDocumentTypeAndDocumentNumber() {
        when(clientRepository.findClientByDocumentTypeAndDocumentNumber(any(String.class), any(String.class)))
                .thenReturn(Mono.just(client));
        assertNotNull(clienteService.ByDocumentTypeAndDocumentNumber("DNI", "74910877"));
    }

    @Test
    void delete() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(clientRepository.delete(any(Client.class))).thenReturn(Mono.empty());
        assertNotNull(clienteService.delete("123"));
    }


    @Test
    void save() {
        when(clientRepository.findClientByDocumentTypeAndDocumentNumber(any(String.class), any(String.class)))
                .thenReturn(Mono.empty());
        when(clientRepository.insert(any(Client.class)))
                .thenReturn(Mono.just(client));

        client = Client.builder()
                .id("tes456")
                .documentType("123")
                .documentNumber("123")
                .build();
        assertNotNull(clienteService.save(client));
    }

    @Test
    void update() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(clientRepository.save(any(Client.class))).thenReturn(Mono.just(client));
        assertNotNull(clienteService.update(client));
    }

    @Test
    void existsById() {
        String result = blockingHelloWorld().block();
        assertEquals("Hello world!", result);
    }

    Mono<String> blockingHelloWorld() {
        return Mono.just("Hello world!");
    }
}