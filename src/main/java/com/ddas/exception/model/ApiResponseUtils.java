package com.ddas.exception.model;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jakarta.servlet.http.HttpServletRequest;

public class ApiResponseUtils
{
    public static <T> ResponseEntity<ApiResponse<T>> buildSuccess(T data)
    {
        return new ResponseEntity<>(new ApiSuccess<T>(data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildError(T data, Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ResponseEntity<>(buildError(data, e, status.value(), req), status);
    }

    public static <T> ResponseEntity<ApiResponse<T>> buildEmptyError(Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ResponseEntity<>(buildError(null, e, status.value(), req), status);
    }

    private static <T> ApiResponse<T> buildError(T data, Exception e, int statusCode, HttpServletRequest req)
    {
        return new ApiError<T>(statusCode, req.getRequestURI(), data, e.getMessage());
    }
}