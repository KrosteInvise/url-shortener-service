package com.kroste.url_shortener_service.service;

import com.kroste.url_shortener_service.dto.ShortenRequest;
import com.kroste.url_shortener_service.dto.ShortenResponse;
import com.kroste.url_shortener_service.dto.UrlStatsResponse;
import org.springframework.stereotype.Service;

@Service
public interface UrlService {
    String getLongUrl(String shortUrl);

    ShortenResponse shortenUrl(ShortenRequest request);

    void incrementStats(String shortKey);

    UrlStatsResponse getStats(String shortKey);
}
