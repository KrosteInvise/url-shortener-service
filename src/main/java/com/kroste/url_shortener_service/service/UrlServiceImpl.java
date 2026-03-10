package com.kroste.url_shortener_service.service;

import com.kroste.url_shortener_service.dto.ShortenRequest;
import com.kroste.url_shortener_service.dto.ShortenResponse;
import com.kroste.url_shortener_service.entity.UrlEntity;
import com.kroste.url_shortener_service.repository.UrlRepository;
import com.kroste.url_shortener_service.util.Base62Converter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;
    private final String DOMAIN = "http://localhost:8080/";

    @Override
    @Transactional
    public ShortenResponse shortenUrl(ShortenRequest request) {
        return urlRepository.findByLongUrl(request.getLongUrl())
                .map(existing -> new ShortenResponse(existing.getLongUrl(), DOMAIN + existing.getShortKey()))
                .orElseGet(() -> createNewShortLink(request.getLongUrl()));
    }

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "urls", key = "#shortKey")
    public String getLongUrl(String shortKey) {
        log.info("Going to base for key: {}", shortKey);
        return urlRepository.findByShortKey(shortKey)
                .map(UrlEntity::getLongUrl)
                .orElseThrow(() -> new EntityNotFoundException("Short link not found: " + shortKey));
    }

    private ShortenResponse createNewShortLink(String longUrl) {
        UrlEntity entity = new UrlEntity();
        entity.setLongUrl(longUrl);
        entity.setCreatedAt(LocalDateTime.now());
        entity = urlRepository.save(entity);

        String key = Base62Converter.encode(entity.getId());
        entity.setShortKey(key);

        return new ShortenResponse(longUrl, DOMAIN + key);
    }
}
