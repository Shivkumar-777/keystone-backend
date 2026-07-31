package com.shivkumar.keystonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TechnicianWorkloadResponse {

    private Long technicianId;

    private String technicianName;

    private long assignedWorkOrders;

}