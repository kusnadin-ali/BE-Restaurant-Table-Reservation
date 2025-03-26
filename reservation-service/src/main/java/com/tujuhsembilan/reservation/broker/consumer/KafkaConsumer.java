package com.tujuhsembilan.reservation.broker.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class KafkaConsumer {
    
    @KafkaListener(topics = "test-topic", groupId = "group3")
    public void consumeMessage(String message) {
        log.info("Received message: " + message);
    }
}
