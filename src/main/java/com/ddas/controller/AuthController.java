package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.model.dto.AuthResponse;
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
    public ResponseEntity<AuthResponse> register(UserRegistrationDTO user)
    {
        return ResponseEntity.ok(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(UserLoginDTO user)
    {
        return ResponseEntity.ok(authService.login(user));
    }

    private final AuthService authService;
}
