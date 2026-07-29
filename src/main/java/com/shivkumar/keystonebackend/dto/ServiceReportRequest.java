package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceReportRequest {

    private String workPerformed;

    private String technicianNotes;

    private String customerFeedback;

    private Double hoursWorked;

    private ServiceStatus status;

    private Long workOrderId;

    private Long technicianId;
}