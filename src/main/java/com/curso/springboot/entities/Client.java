package com.curso.springboot.entities;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@Document("clients")
public class Client {
    @Id
    private String id;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String businessName;
    private String type;
}
