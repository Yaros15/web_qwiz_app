package com.example.web_qwiz_app.web.controller;

import com.example.web_qwiz_app.domain.service.UserService;
import lombok.RequiredArgsConstructor;
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

    @PostMapping
    public void createUser(){}

    @PutMapping("{id}")
    public void updateUser(){}

    @DeleteMapping("{id}")
    public void deleteUser(){}

}
