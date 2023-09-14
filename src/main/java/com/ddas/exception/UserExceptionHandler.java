package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddas.model.exception.ApiError;
import com.ddas.model.exception.ApiErrorUtils;
import com.ddas.model.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiError> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest req)
    {
        return ApiErrorUtils.buildErrorResponse(e, HttpStatus.NOT_FOUND, req);
    }
}
