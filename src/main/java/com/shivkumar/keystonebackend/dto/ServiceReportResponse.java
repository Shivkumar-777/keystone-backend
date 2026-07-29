package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.ServiceAttachment;
import com.shivkumar.keystonebackend.entity.ServiceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

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

    // Uploaded attachments for this service report
    private List<ServiceAttachment> attachments;
}