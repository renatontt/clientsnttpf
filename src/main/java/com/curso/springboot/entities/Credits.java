package com.curso.springboot.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Id;

@Getter
@Setter
public class Credits {

    @Id
    private String id;
    private String type;
    private String idClient;

}
