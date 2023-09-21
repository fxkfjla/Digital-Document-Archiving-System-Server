package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ddas.exception.model.ApiResponse;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler
{
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodNotAllowedException(HttpRequestMethodNotSupportedException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.METHOD_NOT_ALLOWED, req);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ApiResponse<Void>> handleValidationException(ValidationException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.INTERNAL_SERVER_ERROR, req);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }
}
