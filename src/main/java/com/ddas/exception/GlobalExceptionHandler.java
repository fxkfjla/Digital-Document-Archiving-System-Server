package com.ddas.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ApiResponse<Void>>handleDataIntegrityViolationException(DataIntegrityViolationException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.CONFLICT, req);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiResponse<Void>>handleIllegalArgumentException(IllegalArgumentException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ApiResponse<Void>>handleMissingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }
}