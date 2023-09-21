package com.ddas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginRequest(
    @NotNull(message = "Email is null!") 
    @NotBlank(message = "Email is required!") 
    String email, 

    @NotNull(message = "Password is null!") 
    @NotBlank(message = "Password is required!") 
    String password
) { }
