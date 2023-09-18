package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddas.exception.model.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ApiResponse<Void> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.METHOD_NOT_ALLOWED, req);
    }
}
