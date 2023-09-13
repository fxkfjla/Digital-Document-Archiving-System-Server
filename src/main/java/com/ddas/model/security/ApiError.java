package com.ddas.model.security;

public record ApiError(String message, String cause, int statusCode, String timestamp) { }