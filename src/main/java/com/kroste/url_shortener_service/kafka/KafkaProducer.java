package com.kroste.url_shortener_service.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Value("${app.kafka.topics.link-clicks:link-clicks}")
    private String linkClicksTopic;

    public void sendClickEvent(String shortKey) {
        log.info("Send click event: {}", shortKey);
        kafkaTemplate.send(linkClicksTopic, shortKey)
                .whenComplete((result, ex) -> {
                    if (ex != null) {
                        log.error("Failed to send click event [{}] to topic [{}]", shortKey, linkClicksTopic, ex);
                        return;
                    }

                    if (result != null && result.getRecordMetadata() != null) {
                        var metadata = result.getRecordMetadata();
                        log.info("Click event sent [{}], topic={}, partition={}, offset={}",
                                shortKey,
                                metadata.topic(),
                                metadata.partition(),
                                metadata.offset());
                    }
                });
    }
}
