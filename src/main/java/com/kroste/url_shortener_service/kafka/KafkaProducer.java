package com.kroste.url_shortener_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendClickEvent(String shortKey) {
        log.info("Send click event: {}", shortKey);
        kafkaTemplate.send("link-clicks", shortKey);
    }
}
