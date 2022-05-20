package com.group7.clientsservice.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientProducts {
    private Client client;
    private List<Accounts> accounts;
}
