package com.ddas.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ddas.exception.model.ApiError;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class WebSecurityAuthEntryPoint implements AuthenticationEntryPoint
{
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) 
    throws IOException, ServletException
    {
        int statusCode = HttpStatus.UNAUTHORIZED.value();

        var apiError = new ApiError<>(statusCode, request.getRequestURI(), null, authException.getMessage());

        var objMapper = new ObjectMapper();

        response.getWriter().write(objMapper.writeValueAsString(apiError));
        response.setStatus(statusCode);
    }
}