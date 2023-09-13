package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.model.domain.User;
import com.ddas.service.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController
{
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping("/save")
    public ResponseEntity<String> save(User user)
    {
        userService.save(user);
        return ResponseEntity.ok("User saved!");
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<User> findById(Long id)
    {
        return ResponseEntity.ok(userService.findById(id));
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<User> findByEmail(String email)
    {
        return ResponseEntity.ok(userService.findByEmail(email));
    }

    private final UserService userService; 
}
