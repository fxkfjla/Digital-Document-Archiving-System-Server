package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.dto.LoginRequest;
import com.ddas.model.dto.RegisterRequest;
import com.ddas.model.dto.TokenDTO;
import com.ddas.service.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<TokenDTO>> register(@Valid RegisterRequest user)
    {
        return ApiResponse.success(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<TokenDTO>> login(@Valid LoginRequest user)
    {
        return ApiResponse.success(authService.login(user));
    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse<String>> logout(@Valid TokenDTO token)
    {
        authService.logout(token);
        return ApiResponse.success("Token: " + token.token() + " invalidated!");
    }

    private final AuthService authService;
}
