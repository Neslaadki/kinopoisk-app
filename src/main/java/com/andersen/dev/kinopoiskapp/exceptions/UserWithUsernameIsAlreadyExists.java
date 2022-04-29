package com.andersen.dev.kinopoiskapp.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "User with this login is exist in database")
public class UserWithUsernameIsAlreadyExists extends RuntimeException {
    public UserWithUsernameIsAlreadyExists(String message) {
        super(message);
    }
}
