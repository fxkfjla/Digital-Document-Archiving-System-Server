package com.ddas.exception.model;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import jakarta.servlet.http.HttpServletRequest;
import lombok.Getter;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public abstract class ApiResponse<T>
{
    public static <T> ApiSuccess<T> success(T data)
    {
        return new ApiSuccess<T>(data);
    }

    public static <T> ApiSuccess<Void> success()
    {
        return new ApiSuccess<Void>(null);
    }

    public static <T> ApiError<T> error(T data, Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ApiError<T>(status.value(), req.getRequestURI(), data, e.getMessage());
    }

    public static <T> ApiError<Void> error(Exception e, HttpStatus status, HttpServletRequest req)
    {
        return new ApiError<Void>(status.value(), req.getRequestURI(), null, e.getMessage());
    }

    private ApiResponse(int statusCode, String path, T data, String message)
    {
        this.statusCode = statusCode;
        this.path = path;
        this.data = data;
        this.message = message;
        this.timestamp = LocalDateTime.now().toString();
    }

    private static class ApiSuccess<T> extends ApiResponse<T>
    {
        public ApiSuccess(T data)
        {
            super(HttpStatus.OK.value(), null, data, null);
        }
    }

    private static class ApiError<T> extends ApiResponse<T>
    {
        public ApiError(int statusCode, String path, T data, String message)
        {
            super(statusCode, path, data, message);
        }
    }

    private final int statusCode;
    private final String path;
    private final T data;
    private final String message;
    private final String timestamp;
}