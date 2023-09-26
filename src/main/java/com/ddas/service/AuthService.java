package com.ddas.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ddas.exception.model.EmailAlreadyTakenException;
import com.ddas.model.domain.User;
import com.ddas.model.domain.UserRole;
import com.ddas.model.dto.LoginRequest;
import com.ddas.model.dto.RegisterRequest;
import com.ddas.model.dto.TokenRequest;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService
{
    public void register(RegisterRequest userDTO)
    {
        User user = validateRegisterRequest(userDTO);

        userService.save(user);
    }

    public String login(LoginRequest userDTO)
    {
        User user = userService.findByEmail(userDTO.email());

        authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        return jwtService.generateToken(user);
    }

    public void logout(TokenRequest token)
    {
        jwtService.blacklistToken(token.token());
    }

    private User validateRegisterRequest(RegisterRequest user)
    {
        String email = user.email();

        if(userService.existsByEmail(email))
        {
            throw new EmailAlreadyTakenException("Email already taken!");    
        }

        return new User(email, passwordEncoder.encode(user.password()), UserRole.USER);
    }

    private final UserService userService; 
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
}
