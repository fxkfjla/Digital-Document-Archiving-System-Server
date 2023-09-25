package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.EmailAlreadyTakenException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AuthExceptionHandler 
{
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ApiResponse<Void>> handleAuthenticationException(AuthenticationException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.UNAUTHORIZED, req);
    }

    @ExceptionHandler(EmailAlreadyTakenException.class)
    public ResponseEntity<ApiResponse<Void>> handleEmailAlreadyTakenException(EmailAlreadyTakenException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.CONFLICT, req);
    }
}
