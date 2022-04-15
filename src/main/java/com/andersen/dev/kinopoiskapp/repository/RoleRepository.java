package com.andersen.dev.kinopoiskapp.repository;

import com.andersen.dev.kinopoiskapp.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
