package com.ddas.jwt;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ddas.exception.model.ApiResponse;
import com.ddas.service.JwtService;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter
{
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) 
    throws ServletException, IOException
    {
        final String authHeader = request.getHeader(JwtConstants.AUTH_HEADER);    

        if(authHeader == null || !authHeader.startsWith(JwtConstants.AUTH_BEARER))
        {
            filterChain.doFilter(request, response);
            return;
        }

        try
        {
            final String token = authHeader.substring(JwtConstants.BEARER_END_INDEX); 
            final String username = jwtService.extractUsername(token);

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null)
            {
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);

                if(jwtService.tokenIsValid(token, userDetails))
                {
                    UsernamePasswordAuthenticationToken authToken = 
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authToken);
                }
            }
        }
        catch(ExpiredJwtException e)
        {
            // TODO: find a clever way to reduce code repetition (entrypoint)
            HttpStatus status = HttpStatus.UNAUTHORIZED;

            var apiError = ApiResponse.apiError(e, status, request);

            var objMapper = new ObjectMapper();

            response.getWriter().write(objMapper.writeValueAsString(apiError));
            response.setStatus(status.value());
        }
        finally
        {
            filterChain.doFilter(request, response);
        }
    }

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
}   