package com.ddas.model.dto;

//TODO: change to record or create generic class for handling every api response
public class AuthResponse
{
    public AuthResponse(String token)
    {
        this.token = token;
    }

    // Getters, setters
    public String getToken() { return token; }
    public void setToken(String token) { this.token = token; }

    private String token;    
}
