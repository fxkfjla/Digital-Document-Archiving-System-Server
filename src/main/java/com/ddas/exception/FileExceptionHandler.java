package com.ddas.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.ddas.exception.model.ApiResponse;
import com.ddas.exception.model.FileAccessDeniedException;
import com.ddas.exception.model.FileNotFoundException;
import com.ddas.exception.model.FileUploadException;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class FileExceptionHandler
{
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<ApiResponse<Void>> handleMaxSizeException(MaxUploadSizeExceededException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(FileUploadException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileUploadException(FileUploadException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.BAD_REQUEST, req);
    }

    @ExceptionHandler(FileNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileNotFoundException(FileNotFoundException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.NOT_FOUND, req);
    }

    @ExceptionHandler(FileAccessDeniedException.class)
    public ResponseEntity<ApiResponse<Void>> handleFileAccessDeniedException(FileAccessDeniedException e, HttpServletRequest req)
    {
        return ApiResponse.error(e, HttpStatus.FORBIDDEN, req);
    }
}
