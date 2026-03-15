package com.kroste.url_shortener_service.dto;

public record ShortenResponse(
        String longUrl,
        String shortUrl
) {
}