package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.model.dto.UserLoginDTO;
import com.ddas.model.dto.UserRegistrationDTO;
import com.ddas.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController 
{
    public AuthController(AuthService authService)
    {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(UserRegistrationDTO user)
    {
        authService.register(user);
        return ResponseEntity.ok("ok");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(UserLoginDTO user)
    {
        authService.login(user);
        return ResponseEntity.ok("ok");
    }

    private final AuthService authService;
}
