package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Title is required")
    @Size(min = 5, max = 150, message = "Title must be between 5 and 150 characters")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Priority is required")
    private WorkOrderPriority priority;

    @NotNull(message = "Status is required")
    private WorkOrderStatus status;

    @NotNull(message = "Scheduled date is required")
    private LocalDate scheduledDate;

    private LocalDate completedDate;

    @NotNull(message = "Customer ID is required")
    private Long customerId;

    @NotNull(message = "Site ID is required")
    private Long siteId;

    @NotNull(message = "Technician ID is required")
    private Long technicianId;
}