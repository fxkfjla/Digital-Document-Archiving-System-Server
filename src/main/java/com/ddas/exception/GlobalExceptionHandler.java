package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.ApiResponseUtils;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req)
    {
        return ApiResponseUtils.buildError("Method not allowed!", e, HttpStatus.METHOD_NOT_ALLOWED, req);
    }
}
