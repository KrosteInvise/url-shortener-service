package com.kroste.url_shortener_service.controller;

import com.kroste.url_shortener_service.dto.ShortenRequest;
import com.kroste.url_shortener_service.dto.ShortenResponse;
import com.kroste.url_shortener_service.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class UrlUiController {

    private final UrlService urlService;

    @GetMapping
    public String index() {
        return "index";
    }

    @PostMapping
    public String shorten(@RequestParam String longUrl, Model model) {
        ShortenRequest request = new ShortenRequest();
        request.setLongUrl(longUrl);

        ShortenResponse response = urlService.shortenUrl(request);

        model.addAttribute("shortUrl", response.getShortUrl());

        return "index";
    }
}
