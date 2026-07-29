package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Site;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {

    // ==========================
    // Search & Pagination
    // ==========================

    // Search by site name
    Page<Site> findBySiteNameContainingIgnoreCase(
            String siteName,
            Pageable pageable
    );

    // Search by city
    Page<Site> findByCityContainingIgnoreCase(
            String city,
            Pageable pageable
    );

    // Search by state
    Page<Site> findByStateContainingIgnoreCase(
            String state,
            Pageable pageable
    );

    // Search by postal code
    Page<Site> findByPostalCodeContaining(
            String postalCode,
            Pageable pageable
    );

    // Filter by customer
    Page<Site> findByCustomerId(
            Long customerId,
            Pageable pageable
    );
}