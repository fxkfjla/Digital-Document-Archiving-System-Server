package com.ddas.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.model.dto.AuthResponse;
import com.ddas.model.dto.UserLoginDTO;
import com.ddas.model.dto.UserRegistrationDTO;
import com.ddas.security.JwtService;

@Service
public class AuthService
{
    public AuthService(UserService userService, JwtService jwtService, PasswordEncoder passwordEncoder, AuthenticationManager authManager)
    {
        this. userService = userService;
        this.jwtService = jwtService;
        this.passwordEncoder = passwordEncoder;
        this.authManager = authManager;
    }

    public AuthResponse register(UserRegistrationDTO userDTO)
    {
        User user = new User(userDTO.email(), passwordEncoder.encode(userDTO.password()), UserRole.USER);
        userService.save(user);

        return new AuthResponse(jwtService.generateToken(user));
    }

    public AuthResponse login(UserLoginDTO userDTO)
    {
        //TODO: handle authentication exception?
        authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        User user = userService.findByEmail(userDTO.email());

        return new AuthResponse(jwtService.generateToken(user));
    }

    private final UserService userService; 
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
}
