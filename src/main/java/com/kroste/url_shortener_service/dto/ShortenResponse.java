package com.kroste.url_shortener_service.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ShortenResponse {
    private String longUrl;
    private String shortUrl;
}