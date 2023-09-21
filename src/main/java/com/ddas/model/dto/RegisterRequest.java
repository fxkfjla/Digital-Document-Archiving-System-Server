package com.ddas.model.dto;

import com.ddas.model.validation.Password;
import com.ddas.model.validation.PasswordMatches;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@PasswordMatches
public record RegisterRequest(
    @NotNull(message = "Email is null!") 
    @NotBlank(message = "Email is required!") 
    @Size(max = 255, message = "Email too long!")
    @Email(message = "Invalid email format!") 
    String email, 

    @NotNull(message = "Password is null") 
    @NotBlank(message = "Password is required!")
    @Password(message = "Invalid password format!") 
    String password, 

    @NotNull(message = "Repassword is null!") 
    @NotBlank(message = "Repassword is required!") 
    String rePassword
) { }