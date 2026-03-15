package com.kroste.url_shortener_service.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

@Data
public class ShortenRequest {
    @NotBlank(message = "URL cannot be empty")
    @URL(message = "Invalid URL format")
    private String longUrl;

    @Positive(message = "TTL in days must be greater than 0")
    private Integer ttlDays;
}