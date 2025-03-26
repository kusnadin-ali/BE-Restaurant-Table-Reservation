package com.tujuhsembilan.reservation.broker.consumer;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tujuhsembilan.core.constant.BrokerConstant.Group;
import com.tujuhsembilan.core.constant.BrokerConstant.KeyMessage;
import com.tujuhsembilan.core.constant.BrokerConstant.Topic;
import com.tujuhsembilan.core.dto.CustomerBrokerDto;
import com.tujuhsembilan.reservation.model.Customer;
import com.tujuhsembilan.reservation.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomerConsumer {

    private final CustomerRepository customerRepository;
    private final ObjectMapper objectMapper;
    
    @KafkaListener(topics = Topic.CUSTOMER, groupId = Group.RESERVATION)
    public void consumeMessage(ConsumerRecord<String, String> record) {
        try {
            String value = objectMapper.readValue(record.value(), String.class);

            CustomerBrokerDto customerMessage = objectMapper.readValue(value, CustomerBrokerDto.class);
            Customer customer = new Customer();
            customer.setId(customerMessage.getId());
            customer.setName(customerMessage.getName());
            customer.setEmail(customerMessage.getEmail());
            customer.setUsername(customerMessage.getUsername());
            customer.setIsDelete(customerMessage.getIsDelete());
            log.info("{}",customer);
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
