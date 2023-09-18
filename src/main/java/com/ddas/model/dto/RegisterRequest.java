package com.ddas.model.dto;

public record RegisterRequest(String email, String password, String rePassword) { }