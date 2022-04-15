package com.andersen.dev.kinopoiskapp.security;

import com.andersen.dev.kinopoiskapp.model.User;
import com.andersen.dev.kinopoiskapp.security.jwt.JwtUser;
import com.andersen.dev.kinopoiskapp.security.jwt.JwtUserFactory;
import com.andersen.dev.kinopoiskapp.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class JstUserDetailsService implements UserDetailsService {

    @Autowired
    private final UserService userService;

    public JstUserDetailsService(UserService userService) {
        this.userService = userService;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User with username: " + username + " not found");
        }

        JwtUser jwtUser = JwtUserFactory.create(user);
        log.info("IN loadUserByUsername - user with username: {} successfully loaded", username);
        return jwtUser;
    }
}