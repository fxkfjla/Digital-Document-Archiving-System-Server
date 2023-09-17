package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.ApiResponseUtils;
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
    public ResponseEntity<ApiResponse<String>> register(UserRegistrationDTO user)
    {
        return ApiResponseUtils.buildSuccess(authService.register(user));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<String>> login(UserLoginDTO user)
    {
        return ApiResponseUtils.buildSuccess(authService.login(user));
    }

    private final AuthService authService;
}
