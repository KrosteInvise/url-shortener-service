package com.kroste.url_shortener_service.dto;

import java.time.LocalDateTime;

public record UrlStatsResponse(
        String shortKey,
        String longUrl,
        long clickCount,
        LocalDateTime lastAccessedAt,
        LocalDateTime createdAt,
        LocalDateTime expiresAt
) {
}