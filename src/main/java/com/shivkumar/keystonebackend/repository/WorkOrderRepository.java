package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.WorkOrder;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    // Dashboard
    long countByStatus(WorkOrderStatus status);

    // Active work orders
    List<WorkOrder> findByStatusNot(WorkOrderStatus status);

    // SLA already breached
    List<WorkOrder> findBySlaDueDateBeforeAndStatusNot(
            LocalDateTime dateTime,
            WorkOrderStatus status
    );

    // SLA approaching (warning)
    List<WorkOrder> findBySlaDueDateBetweenAndStatusNot(
            LocalDateTime start,
            LocalDateTime end,
            WorkOrderStatus status
    );
}