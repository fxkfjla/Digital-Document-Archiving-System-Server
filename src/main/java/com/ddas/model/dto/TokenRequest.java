package com.ddas.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record TokenRequest(@NotNull(message = "Token is null!") @NotBlank(message = "Token is required!") String token) { }
