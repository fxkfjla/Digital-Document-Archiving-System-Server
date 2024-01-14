package com.ddas.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ddas.exception.model.ApiResponse;
import com.ddas.model.domain.User;
import com.ddas.service.UserService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class UserController
{
    // TODO: authorize admin only

    @PostMapping("/save")
    public ResponseEntity<ApiResponse<String>> save(User user)
    {
        userService.save(user);
        return ApiResponse.success("User saved!");
    }

    @GetMapping("/find-by-id")
    public ResponseEntity<ApiResponse<User>> findById(Long id)
    {
        return ApiResponse.success(userService.findById(id));
    }

    @GetMapping("/find-by-email")
    public ResponseEntity<ApiResponse<User>> findByEmail(String email)
    {
        return ApiResponse.success(userService.findByEmail(email));
    }

    private final UserService userService; 
}
