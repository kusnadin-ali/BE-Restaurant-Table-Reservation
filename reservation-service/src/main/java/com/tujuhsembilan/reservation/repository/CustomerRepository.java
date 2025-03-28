package com.tujuhsembilan.reservation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tujuhsembilan.reservation.model.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    
}
