package com.ddas.service;

import org.springframework.stereotype.Service;

import com.ddas.model.dto.UserLoginDTO;
import com.ddas.model.dto.UserRegistrationDTO;

@Service
public class AuthService
{
    public AuthService(UserService userService)
    {
        this. userService = userService;
    }

    public void register(UserRegistrationDTO userDTO)
    {
        //TODO: Implement register login method!
    }

    public void login(UserLoginDTO userDTO)
    {
        //TODO: Implement auth login method!
    }

    private final UserService userService; 
}
