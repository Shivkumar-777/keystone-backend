package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.WorkOrder;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {

    long countByStatus(WorkOrderStatus status);
}