package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    // ==========================
    // Search & Pagination
    // ==========================

    // Search by customer name
    Page<Customer> findByCustomerNameContainingIgnoreCase(
            String customerName,
            Pageable pageable
    );

    // Search by email
    Page<Customer> findByEmailContainingIgnoreCase(
            String email,
            Pageable pageable
    );

    // Search by phone
    Page<Customer> findByPhoneContaining(
            String phone,
            Pageable pageable
    );
}