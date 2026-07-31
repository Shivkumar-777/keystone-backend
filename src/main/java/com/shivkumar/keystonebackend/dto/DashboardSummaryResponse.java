package com.shivkumar.keystonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DashboardSummaryResponse {

    private long totalCompanies;

    private long totalCustomers;

    private long totalSites;

    private long totalTechnicians;

    private long totalWorkOrders;

    private long openWorkOrders;

    private long inProgressWorkOrders;

    private long completedWorkOrders;

    private long overdueWorkOrders;

    private long lowStockParts;

}