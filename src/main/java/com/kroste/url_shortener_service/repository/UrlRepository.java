package com.kroste.url_shortener_service.repository;

import com.kroste.url_shortener_service.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByShortKey(String shortKey);

    Optional<UrlEntity> findByLongUrl(String longUrl);

    void deleteByCreatedAtBefore(LocalDateTime expiryDate);
}