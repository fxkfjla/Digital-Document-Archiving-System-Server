package com.ddas.exception;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.ddas.exception.model.ApiResponse;
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
        HttpStatus status = HttpStatus.UNAUTHORIZED;

        var apiError = ApiResponse.error(authException, status, request);

        var objMapper = new ObjectMapper();

        response.getWriter().write(objMapper.writeValueAsString(apiError));
        response.setStatus(status.value());
    }
}