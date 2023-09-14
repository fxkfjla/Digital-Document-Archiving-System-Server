package com.ddas.model.exception;

public record ApiError(String message, String cause, int statusCode, String timestamp) { }