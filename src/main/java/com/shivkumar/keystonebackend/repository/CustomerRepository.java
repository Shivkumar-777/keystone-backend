package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}