package com.ddas.exception.model;

public class ApiError<T> extends ApiResponse<T>
{
    public ApiError(int statusCode, String path, T data, String message)
    {
        super(statusCode, path, data, message);
    }
}