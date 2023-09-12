package com.ddas.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.model.User;
import com.ddas.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController
{
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll()
    {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<User> findUserById(Long id)
    {
        return ResponseEntity.ok(userService.findUserById(id));
    }

    private final UserService userService; 
}
