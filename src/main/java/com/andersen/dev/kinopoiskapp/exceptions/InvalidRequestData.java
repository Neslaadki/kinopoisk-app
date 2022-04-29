package com.andersen.dev.kinopoiskapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Wrong parameters format")
public class InvalidRequestData extends RuntimeException {

    public InvalidRequestData(String message) {
        super(message);
    }
}
