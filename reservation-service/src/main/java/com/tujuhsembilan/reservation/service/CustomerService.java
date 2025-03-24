package com.tujuhsembilan.reservation.service;

import org.springframework.stereotype.Service;

import com.tujuhsembilan.reservation.repository.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final CustomerRepository customerRepository;
}
