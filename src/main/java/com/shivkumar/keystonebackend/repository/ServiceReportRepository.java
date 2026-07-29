package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.ServiceReport;
import com.shivkumar.keystonebackend.entity.ServiceStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Long> {

    // ==========================
    // Filter by Work Order
    // ==========================

    Page<ServiceReport> findByWorkOrderId(
            Long workOrderId,
            Pageable pageable
    );

    // ==========================
    // Filter by Technician
    // ==========================

    Page<ServiceReport> findByTechnicianId(
            Long technicianId,
            Pageable pageable
    );

    // ==========================
    // Filter by Status
    // ==========================

    Page<ServiceReport> findByStatus(
            ServiceStatus status,
            Pageable pageable
    );

    // ==========================
    // Filter by Report Date
    // ==========================

    Page<ServiceReport> findByReportDateBetween(
            LocalDateTime start,
            LocalDateTime end,
            Pageable pageable
    );

    // ==========================
    // Search Work Performed
    // ==========================

    Page<ServiceReport> findByWorkPerformedContainingIgnoreCase(
            String workPerformed,
            Pageable pageable
    );

    // ==========================
    // Search Technician Notes
    // ==========================

    Page<ServiceReport> findByTechnicianNotesContainingIgnoreCase(
            String technicianNotes,
            Pageable pageable
    );

    // ==========================
    // Search Customer Feedback
    // ==========================

    Page<ServiceReport> findByCustomerFeedbackContainingIgnoreCase(
            String customerFeedback,
            Pageable pageable
    );
}