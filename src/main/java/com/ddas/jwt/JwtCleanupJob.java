package com.ddas.jwt;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.ddas.service.JwtService;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JwtCleanupJob
{
    @Scheduled(cron = "0 0 */1 * * *")
    public void cleanupExpiredTokens()
    {
        jwtService.deleteExpiredTokens();
    }

    private final JwtService jwtService;
}