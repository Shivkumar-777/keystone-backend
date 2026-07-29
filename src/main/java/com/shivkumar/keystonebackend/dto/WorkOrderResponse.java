package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrderResponse {

    private Long id;

    private String title;

    private String description;

    private WorkOrderPriority priority;

    private WorkOrderStatus status;

    private LocalDate scheduledDate;

    private LocalDate completedDate;

    private LocalDateTime createdAt;

    private Long customerId;
    private String customerName;

    private Long siteId;
    private String siteName;

    private Long technicianId;
    private String technicianName;
}