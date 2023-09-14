package com.ddas.model.exception;

import java.time.LocalDateTime;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public class ApiErrorUtils
{
    public static ApiError generateApiError(Exception e, int statusCode, HttpServletRequest req)
    {
        ApiError apiError = new ApiError
        (
            e.getMessage(), 
            statusCode, 
            LocalDateTime.now().toString(), 
            req.getRequestURI(), 
            ExceptionUtils.getStackTrace(e)
        );

        return apiError;
    }

    public static ResponseEntity<ApiError> buildErrorResponse(Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ResponseEntity<>(generateApiError(e, status.value(), req), status);
    }
}