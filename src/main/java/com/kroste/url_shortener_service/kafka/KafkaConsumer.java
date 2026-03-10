package com.kroste.url_shortener_service.kafka;

import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {

    @KafkaListener(topics = "link-clicks", groupId = "url-shortener-group")
    public void listen(String shortKey) {
        log.info("Catch click by link: {}", shortKey);
        //urlRepository.incrementClick(shortKey); позже сделаю метод
    }
}
