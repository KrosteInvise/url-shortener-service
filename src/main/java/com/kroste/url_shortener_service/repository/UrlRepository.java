package com.kroste.url_shortener_service.repository;

import com.kroste.url_shortener_service.entity.UrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepository extends JpaRepository<UrlEntity, Long> {
    Optional<UrlEntity> findByShortKey(String shortKey);
}
