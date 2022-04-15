package com.andersen.dev.kinopoiskapp.rest;

import com.andersen.dev.kinopoiskapp.dto.AuthenticationRequestDto;
import com.andersen.dev.kinopoiskapp.dto.RegistrationRequestDto;
import com.andersen.dev.kinopoiskapp.mappers.RegistrationRequestMapper;
import com.andersen.dev.kinopoiskapp.model.User;
import com.andersen.dev.kinopoiskapp.security.jwt.JwtTokenProvider;
import com.andersen.dev.kinopoiskapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/auth/")
@RequiredArgsConstructor
public class AuthenticationRestControllerV1 {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserService userService;

    private final RegistrationRequestMapper registrationRequestMapper;

//    @Autowired
//    public AuthenticationRestControllerV1(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserService userService) {
//        this.authenticationManager = authenticationManager;
//        this.jwtTokenProvider = jwtTokenProvider;
//        this.userService = userService;
//    }

    @PostMapping("login")
    public ResponseEntity<?> login(@RequestBody AuthenticationRequestDto requestDto) {
        try {
            String username = requestDto.getUsername();
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
            User user = userService.findByUsername(username);

            if (user == null) {
                throw new UsernameNotFoundException("User with username: " + username + " not found");
            }

            String token = jwtTokenProvider.createToken(username, user.getRoles());

            Map<Object, Object> response = new HashMap<>();
            response.put("username", username);
            response.put("token", token);

            return ResponseEntity.ok(response);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username or password");
        }
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> register(@RequestBody RegistrationRequestDto requestDto){
        try {
            User user = userService.register(registrationRequestMapper.fromDto(requestDto));
            return ResponseEntity.ok(new HashMap<>().put("username", user.getUsername()));
        }catch (Exception e) {
            throw new BadCredentialsException(e.getMessage());
        }
    }

    // сделать регистрацию
}
