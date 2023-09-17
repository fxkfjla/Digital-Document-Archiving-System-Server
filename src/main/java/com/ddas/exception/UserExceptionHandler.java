package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.ApiResponseUtils;
import com.ddas.exception.model.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ApiResponse<String>> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest req)
    {
        return ApiResponseUtils.buildError("User not found!", e, HttpStatus.NOT_FOUND, req);
    }
}
