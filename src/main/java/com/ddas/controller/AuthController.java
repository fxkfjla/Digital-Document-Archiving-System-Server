package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.dto.LoginRequest;
import com.ddas.model.dto.RegisterRequest;
import com.ddas.service.AuthService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<String>> register(@Valid RegisterRequest user)
    {
        return ApiResponse.success(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(@Valid LoginRequest user)
    {
        return ApiResponse.success(authService.login(user));
    }

    // TODO: validate token handle invalid token exception 
    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@Valid @NotNull String token)
    {
        authService.logout(token);
        return ApiResponse.success("Token: " + token + " invalidated!");
    }

    private final AuthService authService;
}
