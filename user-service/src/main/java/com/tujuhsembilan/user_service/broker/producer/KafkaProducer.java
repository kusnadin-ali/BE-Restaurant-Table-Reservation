package com.tujuhsembilan.user_service.broker.producer;

import java.util.concurrent.CompletableFuture;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final ObjectMapper objectMapper;

    public void sendMessage(Object object, String topicName, String key) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(object);

            CompletableFuture<SendResult<String, String>> result = kafkaTemplate.send(topicName, key, jsonMessage);

            result.whenComplete((sendResult, throwable) -> {
                if (throwable != null) {
                    log.error("Error sending message: {}", throwable.getMessage(), throwable);
                } else {
                    log.info("Message sent successfully: {}", sendResult.getProducerRecord().value());
                }
            });

        } catch (JsonProcessingException e) {
            log.error("Error converting message to JSON: {}", e.getMessage(), e);
        }
    }
}
