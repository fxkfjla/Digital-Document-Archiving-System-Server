package com.ddas.exception.model;

public class FileAccessDeniedException extends RuntimeException
{
    public FileAccessDeniedException(String message)
    {
        super(message);
    }
}
