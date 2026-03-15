package com.kroste.url_shortener_service.controller;

import com.kroste.url_shortener_service.dto.ShortenRequest;
import com.kroste.url_shortener_service.dto.ShortenResponse;
import com.kroste.url_shortener_service.dto.UrlStatsResponse;
import com.kroste.url_shortener_service.service.UrlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "api/v1/urls")
public class UrlController {
    private final UrlService urlService;

    @PostMapping("/shorten")
    public ResponseEntity<ShortenResponse> shorten(@Valid @RequestBody ShortenRequest request) {
        ShortenResponse response = urlService.shortenUrl(request);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{shortKey}/stats")
    public ResponseEntity<UrlStatsResponse> stats(@PathVariable String shortKey) {
        UrlStatsResponse response = urlService.getStats(shortKey);
        return ResponseEntity.ok(response);
    }
}

