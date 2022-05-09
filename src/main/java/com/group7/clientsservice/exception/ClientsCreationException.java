package com.group7.clientsservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class ClientsCreationException extends RuntimeException {

    public ClientsCreationException(String message) {
        super(message);
    }
}
