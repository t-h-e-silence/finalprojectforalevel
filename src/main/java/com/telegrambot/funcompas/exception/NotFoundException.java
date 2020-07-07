package com.telegrambot.funcompas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class NotFoundException extends ResponseStatusException {

    public NotFoundException(String request) {
        super(HttpStatus.NOT_FOUND, "Ничего не надено!");
    }
}


