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
import com.ddas.model.dto.TokenDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class AuthService
{
    public TokenDTO register(RegisterRequest userDTO)
    {
        User user = validateRegisterRequest(userDTO);

        userService.save(user);

        return new TokenDTO(jwtService.generateToken(user));
    }

    public TokenDTO login(LoginRequest userDTO)
    {
        User user = userService.findByEmail(userDTO.email());

        authManager.authenticate(new UsernamePasswordAuthenticationToken(userDTO.email(), userDTO.password()));

        return new TokenDTO(jwtService.generateToken(user));
    }

    public void logout(TokenDTO token)
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
