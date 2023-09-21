package com.ddas.service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.ddas.model.domain.JwtBlacklist;
import com.ddas.repository.JwtBlacklistRepository;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class JwtService
{
    public String generateToken(UserDetails userDetails)
    {
        return generateToken(userDetails, new HashMap<>());
    }

    public String generateToken(UserDetails userDetails, Map<String, Object> extraClaims)
    {
        return Jwts.builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .setIssuedAt(new Date(System.currentTimeMillis()))
        .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
        .signWith(getSigningKey(), SignatureAlgorithm.HS256)
        .compact();
    }

    public boolean tokenIsValid(String token, UserDetails userDetails)
    {
        final String username = extractUsername(token);
        return username.equals(userDetails.getUsername()) && !tokenIsExpired(token) && !tokenIsBlacklisted(token);
    }

    public boolean tokenIsBlacklisted(String token)
    {
        return jwtBlacklistRepository.existsByToken(token);
    }

    public void blacklistToken(String token)
    {
        jwtBlacklistRepository.save(new JwtBlacklist(token));
    }

    public String extractUsername(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    private boolean tokenIsExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token)
    {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimExtractor)
    {
        Claims claims = extractAllClaims(token);
        return claimExtractor.apply(claims);
    }

    private Claims extractAllClaims(String token)
    {
        return Jwts.parserBuilder()
        .setSigningKey(getSigningKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
    }

    private Key getSigningKey()
    {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY); 
        return Keys.hmacShaKeyFor(keyBytes);
    }

    // Possibly move to application.properties
    private final String SECRET_KEY = "4c63e2fd3a1fbc442a2a6646e1c2df6e52449e4c464ea7f7e3e2a6e8e471b289";
    private final long tokenExpiration = 1000 * 60 * 24;

    private final JwtBlacklistRepository jwtBlacklistRepository;
}
