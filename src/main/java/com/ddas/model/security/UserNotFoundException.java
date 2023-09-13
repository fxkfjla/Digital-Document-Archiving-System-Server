package com.ddas.model.security;

public class UserNotFoundException extends RuntimeException
{
    public UserNotFoundException(String message)
    {
        super(message);
    } 

    public UserNotFoundException(String message, Throwable cause)
    {
        super(message, cause);
    }
}
