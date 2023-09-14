package com.ddas.exception;

import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.ddas.model.exception.ApiError;
import com.ddas.model.exception.UserNotFoundException;

import jakarta.servlet.http.HttpServletRequest;

@ControllerAdvice
public class UserExceptionHandler
{
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException e, HttpServletRequest req)
    {
        HttpStatus status = HttpStatus.NOT_FOUND;

        return new ResponseEntity<>(generateApiError(e, status.value(), req.getRequestURI()), HttpStatus.NOT_FOUND);
    }

    private Object generateApiError(Exception e, int statusCode, String reqPath)
    {
        return new ApiError(e.getMessage(), statusCode, LocalDateTime.now().toString(), reqPath, ExceptionUtils.getStackTrace(e));
    }
}
