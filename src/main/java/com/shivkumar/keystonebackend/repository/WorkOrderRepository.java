package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.WorkOrder;
import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    // ==========================
    // Dashboard
    // ==========================

    long countByStatus(WorkOrderStatus status);

    long countByPriority(WorkOrderPriority priority);

    long countByTechnicianId(Long technicianId);

    // Count overdue work orders
    long countBySlaDueDateBeforeAndStatusNot(
            LocalDateTime dateTime,
            WorkOrderStatus status
    );

    List<WorkOrder> findByStatus(WorkOrderStatus status);

    // ==========================
    // Active Work Orders
    // ==========================

    List<WorkOrder> findByStatusNot(WorkOrderStatus status);

    // ==========================
    // SLA Monitoring
    // ==========================

    List<WorkOrder> findBySlaDueDateBeforeAndStatusNot(
            LocalDateTime dateTime,
            WorkOrderStatus status
    );

    List<WorkOrder> findBySlaDueDateBetweenAndStatusNot(
            LocalDateTime start,
            LocalDateTime end,
            WorkOrderStatus status
    );

    // ==========================
    // Search & Filtering
    // ==========================

    // Search by title
    Page<WorkOrder> findByTitleContainingIgnoreCase(
            String title,
            Pageable pageable
    );

    // Filter by status
    Page<WorkOrder> findByStatus(
            WorkOrderStatus status,
            Pageable pageable
    );

    // Filter by priority
    Page<WorkOrder> findByPriority(
            WorkOrderPriority priority,
            Pageable pageable
    );

    // Filter by customer
    Page<WorkOrder> findByCustomerId(
            Long customerId,
            Pageable pageable
    );

    // Filter by technician
    Page<WorkOrder> findByTechnicianId(
            Long technicianId,
            Pageable pageable
    );

    // Filter by site
    Page<WorkOrder> findBySiteId(
            Long siteId,
            Pageable pageable
    );
}