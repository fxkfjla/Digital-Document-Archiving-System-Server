package com.ddas.exception.model;

public class EmailAlreadyTakenException extends RuntimeException
{
    public EmailAlreadyTakenException(String message)
    {
        super(message);
    }
}
