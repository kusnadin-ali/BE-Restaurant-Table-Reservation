package com.tujuhsembilan.user_service.broker.producer;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.core.constant.BrokerConstant.Topic;
import com.tujuhsembilan.core.dto.CustomerBrokerDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerProducer {

    private final KafkaProducer kafkaProducer;
    private final ObjectMapper objectMapper;

    public void sendMessage(CustomerBrokerDto customer, String key) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(customer);
            kafkaProducer.sendMessage(jsonMessage, Topic.CUSTOMER, key);
        } catch (Exception e) {
            log.error("Error sending Customer Topic message: {}", e.getMessage(), e);
        }
    }
}
