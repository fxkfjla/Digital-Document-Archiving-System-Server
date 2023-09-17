package com.ddas.exception.model;

import org.springframework.http.HttpStatus;

public class ApiSuccess<T> extends ApiResponse<T>
{
    public ApiSuccess(T data)
    {
        super(HttpStatus.OK.value(), null, data, null);
    }
}
