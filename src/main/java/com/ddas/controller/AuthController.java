package com.ddas.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.dto.LoginRequest;
import com.ddas.model.dto.RegisterRequest;
import com.ddas.service.AuthService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController
{


    @PostMapping("/regster")
    public ApiResponse<String> register(RegisterRequest user)
    {
        return ApiResponse.success(authService.register(user));
    }

    @PostMapping("/login")
    public ApiResponse<String> login(LoginRequest user)
    {
        return ApiResponse.success(authService.login(user));
    }

    private final AuthService authService;
}
