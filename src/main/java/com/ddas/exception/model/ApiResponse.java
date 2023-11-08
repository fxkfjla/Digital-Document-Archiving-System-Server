package com.ddas.exception.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ApiResponse<T>
{
    public static <T> ResponseEntity<ApiResponse<T>> success(T data, HttpHeaders headers)
    {
        return new ResponseEntity<ApiResponse<T>>(new ApiSuccess<T>(data), headers, HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(T data)
    {
        return new ResponseEntity<ApiResponse<T>>(new ApiSuccess<T>(data), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> success(HttpHeaders headers)
    {
        return new ResponseEntity<ApiResponse<T>>(new ApiSuccess<T>(null), headers, HttpStatus.OK);
    }

    public static ResponseEntity<ApiResponse<Void>> success()
    {
        return new ResponseEntity<ApiResponse<Void>>(new ApiSuccess<Void>(null), HttpStatus.OK);
    }

    public static <T> ResponseEntity<ApiResponse<T>> error(T data, Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ResponseEntity<ApiResponse<T>>(new ApiError<T>(status, req.getRequestURI(), data, e.getMessage()), status);
    }

    public static ResponseEntity<ApiResponse<Void>> error(Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ResponseEntity<ApiResponse<Void>>(new ApiError<Void>(status, req.getRequestURI(), null, e.getMessage()), status);
    }

    public static ApiResponse<Void> apiError(Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ApiError<Void>(status, req.getRequestURI(), null, e.getMessage());
    }

    private ApiResponse(HttpStatus status, String path, T data, String message)
    {
        this.status = status;
        this.path = path;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    private static class ApiSuccess<T> extends ApiResponse<T>
    {
        public ApiSuccess(T data)
        {
            super(HttpStatus.OK, null, data, null);
        }
    }

    private static class ApiError<T> extends ApiResponse<T>
    {
        public ApiError(HttpStatus status, String path, T data, String message)
        {
            super(status, path, data, message);
        }
    }

    private final HttpStatus status;
    private final String path;
    private final T data;
    private final String message;
    private final String timestamp;
}