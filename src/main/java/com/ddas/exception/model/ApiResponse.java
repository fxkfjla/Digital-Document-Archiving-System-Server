package com.ddas.exception.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ApiResponse<T>
{
    public ApiResponse(int statusCode, String path, T data, String message)
    {
        this.statusCode = statusCode;
        this.path = path;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    // Getters
    public T getData() { return data; }
    public int getStatusCode() { return statusCode; }
    public String getMessage() { return message; }
    public String getPath() { return path; }
    public String getTimeStamp() { return timestamp; }

    private final int statusCode;
    private final String path;
    private final T data;
    private final String message;
    private final String timestamp;
}