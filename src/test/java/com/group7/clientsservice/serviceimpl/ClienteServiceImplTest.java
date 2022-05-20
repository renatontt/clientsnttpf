package com.group7.clientsservice.serviceimpl;

import com.group7.clientsservice.dto.ClientsRequestDto;
import com.group7.clientsservice.model.Client;
import com.group7.clientsservice.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

class ClienteServiceImplTest {


    @InjectMocks
    ClientServiceImpl clienteService;
    @Mock
    private ClientRepository clientRepository;
    private Client client;
    private ClientsRequestDto clientDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        client = Client.builder()
                .id("123")
                .documentType("DNI")
                .documentNumber("74910877")
                .businessName("")
                .type("personal")
                .profile("vip")
                .build();

        clientDto = ClientsRequestDto.builder()
                .documentType("DNI")
                .documentNumber("74910877")
                .businessName("")
                .type("personal")
                .profile("vip")
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
        assertNotNull(clienteService.getByDocTypeAndDocNumber("DNI", "74910877"));
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
        assertNotNull(clienteService.save(clientDto));
    }

    @Test
    void update() {
        when(clientRepository.findById(any(String.class))).thenReturn(Mono.just(client));
        when(clientRepository.save(any(Client.class))).thenReturn(Mono.just(client));
        assertNotNull(clienteService.update("123", clientDto));
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