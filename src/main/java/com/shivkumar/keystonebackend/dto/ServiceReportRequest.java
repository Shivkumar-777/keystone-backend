package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.ServiceStatus;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceReportRequest {

    @NotBlank(message = "Work performed is required")
    @Size(min = 10, max = 2000, message = "Work performed must be between 10 and 2000 characters")
    private String workPerformed;

    @Size(max = 1000, message = "Technician notes cannot exceed 1000 characters")
    private String technicianNotes;

    @Size(max = 1000, message = "Customer feedback cannot exceed 1000 characters")
    private String customerFeedback;

    @NotNull(message = "Hours worked is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Hours worked must be greater than 0")
    private Double hoursWorked;

    @NotNull(message = "Service status is required")
    private ServiceStatus status;

    @NotNull(message = "Work Order ID is required")
    private Long workOrderId;

    @NotNull(message = "Technician ID is required")
    private Long technicianId;
}