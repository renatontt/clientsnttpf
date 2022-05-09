package com.group7.clientsservice.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("clients")
@AllArgsConstructor
public class Client {

    private String id;
    private String documentType;
    private String documentNumber;
    private String firstName;
    private String lastName;
    private String businessName;
    private String type;
}
