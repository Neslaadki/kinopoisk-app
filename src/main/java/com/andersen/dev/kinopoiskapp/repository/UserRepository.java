package com.andersen.dev.kinopoiskapp.repository;

import com.andersen.dev.kinopoiskapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
