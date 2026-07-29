package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    // ==========================
    // Dashboard
    // ==========================

    long countByStatus(TechnicianStatus status);

    // ==========================
    // Search & Pagination
    // ==========================

    // Search by technician name
    Page<Technician> findByFullNameContainingIgnoreCase(
            String fullName,
            Pageable pageable
    );

    // Search by email
    Page<Technician> findByEmailContainingIgnoreCase(
            String email,
            Pageable pageable
    );

    // Search by phone
    Page<Technician> findByPhoneContaining(
            String phone,
            Pageable pageable
    );

    // Filter by status
    Page<Technician> findByStatus(
            TechnicianStatus status,
            Pageable pageable
    );

    // Search by specialization
    Page<Technician> findBySpecializationContainingIgnoreCase(
            String specialization,
            Pageable pageable
    );
}