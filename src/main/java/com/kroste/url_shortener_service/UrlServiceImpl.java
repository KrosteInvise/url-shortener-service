package com.kroste.url_shortener_service;

import com.kroste.url_shortener_service.entity.UrlEntity;
import com.kroste.url_shortener_service.repository.UrlRepository;
import com.kroste.url_shortener_service.service.UrlService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UrlServiceImpl implements UrlService {

    private final UrlRepository urlRepository;

    @Override
    @Transactional(readOnly = true)
    public String getLongUrl(String shortKey) {
        return urlRepository.findByShortKey(shortKey)
                .map(UrlEntity::getLongUrl)
                .orElseThrow(() -> new EntityNotFoundException("Short link not found: " + shortKey));
    }
}
