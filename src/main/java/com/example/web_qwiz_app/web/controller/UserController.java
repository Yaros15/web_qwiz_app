package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public void getAllUser(){
    }

    @GetMapping("{id}")
    public void findUserById(){}

    @PutMapping("{id}")
    public void updateUser(){}

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(){}

}
