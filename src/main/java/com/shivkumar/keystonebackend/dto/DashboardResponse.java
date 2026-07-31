package com.shivkumar.keystonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardResponse {

    // ==========================
    // Company
    // ==========================

    private Long totalCompanies;

    // ==========================
    // Customer
    // ==========================

    private Long totalCustomers;

    // ==========================
    // Site
    // ==========================

    private Long totalSites;

    // ==========================
    // Technician
    // ==========================

    private Long totalTechnicians;

    // ==========================
    // Work Order
    // ==========================

    private Long totalWorkOrders;

    // ==========================
    // Inventory
    // ==========================

    private Long totalInventoryParts;

    // ==========================
    // Service Report
    // ==========================

    private Long totalServiceReports;

    // ==========================
    // Work Order Status
    // ==========================

    private Long openWorkOrders;

    private Long assignedWorkOrders;

    private Long inProgressWorkOrders;

    private Long completedWorkOrders;

    private Long cancelledWorkOrders;

    // New field
    private Long overdueWorkOrders;

    // ==========================
    // Technician Status
    // ==========================

    private Long availableTechnicians;

    private Long busyTechnicians;

    private Long offlineTechnicians;

    // ==========================
    // Inventory
    // ==========================

    private Long lowStockItems;

    private Long outOfStockItems;
}