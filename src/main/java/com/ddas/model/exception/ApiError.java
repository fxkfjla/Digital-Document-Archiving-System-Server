package com.ddas.model.exception;

public record ApiError(String message, int statusCode, String timestamp, String path, String cause) { }