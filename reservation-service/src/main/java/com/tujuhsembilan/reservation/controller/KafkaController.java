package com.tujuhsembilan.reservation.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tujuhsembilan.reservation.broker.producer.KafkaProducer;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/kafka")
@RequiredArgsConstructor
public class KafkaController {
    
    private final KafkaProducer kafkaProducer;

    @PostMapping("")
    public ResponseEntity<?> postMethodName(@RequestBody Object request) {
        kafkaProducer.sendMessage(request);
        
        return ResponseEntity.ok().build();
    }
    
}
