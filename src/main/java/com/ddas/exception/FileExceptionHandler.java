package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.FileException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class FileExceptionHandler
{
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleMaxSizeException(MaxUploadSizeExceededException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(FileException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileException(FileException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }
}
