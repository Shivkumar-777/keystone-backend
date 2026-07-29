package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceReportResponse {

    private Long id;

    private String workPerformed;

    private String technicianNotes;

    private String customerFeedback;

    private Double hoursWorked;

    private ServiceStatus status;

    private LocalDateTime reportDate;

    private Long workOrderId;

    private String workOrderTitle;

    private Long technicianId;

    private String technicianName;
}