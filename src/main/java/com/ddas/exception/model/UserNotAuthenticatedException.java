package com.ddas.exception.model;

public class UserNotAuthenticatedException extends RuntimeException
{
    public UserNotAuthenticatedException(String message)
    {
        super(message);
    } 
}
