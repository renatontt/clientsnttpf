package com.curso.springboot.repository;

import com.curso.springboot.entities.Client;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends ReactiveMongoRepository<Client, String> {
}
