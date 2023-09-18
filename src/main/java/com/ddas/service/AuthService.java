package com.ddas.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.model.dto.LoginRequest;
import com.ddas.model.dto.RegisterRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService
{
    public String register(RegisterRequest userDTO)
    {
        User user = new User(userDTO.email(), passwordEncoder.encode(userDTO.password()), UserRole.USER);
        userService.save(user);

        return jwtService.generateToken(user);
    }

    public String login(LoginRequest userDTO)
    {
        //TODO: handle authentication exception?
        User user = userService.findByEmail(userDTO.email());

        authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        return jwtService.generateToken(user);
    }

    private final UserService userService; 
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
}
