package com.andersen.dev.kinopoiskapp.rest;

import com.andersen.dev.kinopoiskapp.dto.AdminUserDto;
import com.andersen.dev.kinopoiskapp.model.User;
import com.andersen.dev.kinopoiskapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "users/{id}")
    public ResponseEntity<AdminUserDto> getUserById(@PathVariable(name = "id") Long id) {
        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        AdminUserDto result = AdminUserDto.fromUser(user);

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<AdminUserDto>> getUserList() {
        List<User> user = userService.getAll();
        List<AdminUserDto> adminUserDtos = new ArrayList<>();
        user.forEach(u -> {
            adminUserDtos.add(AdminUserDto.fromUser(u));
        });
        return new ResponseEntity<>(adminUserDtos, HttpStatus.OK);
    }


}
