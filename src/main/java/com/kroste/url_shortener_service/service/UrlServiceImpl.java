package com.kroste.url_shortener_service.service;

import com.kroste.url_shortener_service.dto.ShortenRequest;
import com.kroste.url_shortener_service.dto.ShortenResponse;
import com.kroste.url_shortener_service.entity.UrlEntity;
import com.kroste.url_shortener_service.repository.UrlRepository;
import com.kroste.url_shortener_service.util.Base62Converter;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "urls", key = "#shortKey")
    public String getLongUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(UrlEntity::getLongUrl)
                .orElseThrow(() -> new EntityNotFoundException("Short link not found: " + shortKey));
    }

    @Override
    @Transactional
    public ShortenResponse shortenUrl(ShortenRequest request) {
        UrlEntity urlEntity = new UrlEntity();
        urlEntity.setLongUrl(request.getLongUrl());
        urlEntity = urlRepository.save(urlEntity);

        String shortKey = Base62Converter.encode(urlEntity.getId());
        urlEntity.setShortKey(shortKey);

        return new ShortenResponse(
                urlEntity.getLongUrl(),
                "http://localhost:8080/" + shortKey //Потом вынесу домен в сеттингсы
        );
    }
}
