package com.curso.springboot.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Document("clientes")
public class Client {

    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String telefono;
    private String direccion;
}
