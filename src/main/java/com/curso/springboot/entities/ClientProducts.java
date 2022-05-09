package com.curso.springboot.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientProducts {
    private Client client;
    private List<Accounts> accounts;
    private List<Credits> credits;
}
