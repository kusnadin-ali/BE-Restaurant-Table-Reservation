package com.tujuhsembilan.reservation.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.core.broker.model.Customer;
import com.tujuhsembilan.core.constant.BrokerConstant.KeyMessage;
import com.tujuhsembilan.reservation.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerConsumer {

    private CustomerRepository customerRepository;
    private ObjectMapper objectMapper;
    
    @KafkaListener(topics = "consumer", groupId = "reservation")
    public void consumeMessage(ConsumerRecord<String, String> record) {
        try {
            Customer customer = objectMapper.readValue(record.value(), Customer.class);
            switch (record.key()) {
                case KeyMessage.CREATE:
                    customerRepository.save(customer);
                    break;
                case KeyMessage.UPDATE:
                    customerRepository.save(customer);
                    break;
                case KeyMessage.DELETE:
                    customerRepository.delete(customer);
                    break;
                default:
                    log.error("Unknown key: {}", record.key());
                    break;
            }
        } catch (Exception e) {
            log.error("Error consuming message on Customer Topic: {}", e);
        }
    }
}
