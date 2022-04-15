package com.andersen.dev.kinopoiskapp.service;

import com.andersen.dev.kinopoiskapp.model.User;

import java.util.List;

public interface UserService {

    User register(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);
}
