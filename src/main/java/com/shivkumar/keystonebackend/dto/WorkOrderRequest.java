package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderRequest {

    private String title;

    private String description;

    private WorkOrderPriority priority;

    private WorkOrderStatus status;

    private LocalDate scheduledDate;

    private LocalDate completedDate;

    private Long customerId;

    private Long siteId;

    private Long technicianId;
}