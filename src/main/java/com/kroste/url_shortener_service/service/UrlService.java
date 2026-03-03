package com.kroste.url_shortener_service.service;

import org.springframework.stereotype.Service;

@Service
public interface UrlService {

    public String getLongUrl(String shortUrl);
}
