package com.andersen.dev.kinopoiskapp;

import com.andersen.dev.kinopoiskapp.exceptions.EntitiesIsAlreadyUploaded;
import com.andersen.dev.kinopoiskapp.model.Role;
import com.andersen.dev.kinopoiskapp.model.User;
import com.andersen.dev.kinopoiskapp.repository.ContentRepository;
import com.andersen.dev.kinopoiskapp.repository.RoleRepository;
import com.andersen.dev.kinopoiskapp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class InitialComp {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    @Transactional(rollbackFor = {
            DataIntegrityViolationException.class,
            ConstraintViolationException.class,
            EntitiesIsAlreadyUploaded.class
            })
    @EventListener(ApplicationReadyEvent.class)
    public void init() {
        long roleCount = roleRepository.count();
        if (roleCount == 0) {
            List<Role> roles = Arrays.asList(
                    new Role().setName("ROLE_USER"),
                    new Role().setName("ROLE_ADMIN")
            );
            roleRepository.saveAll(roles);
            log.info("IN initialComp - init: roles created, count : {}", roleRepository.count());

            User user = new User().setUsername("default").setEmail("default@gmail.com")
                    .setFirstName("Default").setLastName("Default")
                    .setPassword("$2a$12$l.Xt2/VCUJlVx.xOsMyP9ucvTDCDoX8hJsx5rOO5JAGkwiWMy/Jvy")
                    .setRoles(roles);
            userRepository.save(user);
            log.info("IN initialComp - init: default user with username : {}, email : {}, password : {} created", user.getUsername(), user.getEmail(), "default");
        }

    }

    //цепочка фильтров,  ContextHolder

}
