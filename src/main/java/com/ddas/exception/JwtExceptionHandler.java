package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ddas.exception.model.ApiResponse;

import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class JwtExceptionHandler 
{
    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ApiResponse<Void>> handleMalformedJwtException(MalformedJwtException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }
    
    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ApiResponse<Void>> handleSignatureException(SignatureException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }
}
