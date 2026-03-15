package com.kroste.url_shortener_service.controller;

import com.kroste.url_shortener_service.kafka.KafkaProducer;
import com.kroste.url_shortener_service.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
@RequestMapping("/")
@RequiredArgsConstructor
public class RedirectController {

    private final UrlService urlService;
    private final KafkaProducer kafkaProducer;

    @GetMapping("/{shortKey}")
    public ResponseEntity<Void> redirect(@PathVariable String shortKey) {
        String longUrl = urlService.getLongUrl(shortKey);
        urlService.incrementStats(shortKey);

        kafkaProducer.sendClickEvent(shortKey);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(longUrl))
                .build();
    }
}