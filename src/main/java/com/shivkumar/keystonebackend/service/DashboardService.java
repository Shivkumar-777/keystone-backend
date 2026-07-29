package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.DashboardResponse;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import com.shivkumar.keystonebackend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService {

    private final CompanyRepository companyRepository;
    private final CustomerRepository customerRepository;
    private final SiteRepository siteRepository;
    private final TechnicianRepository technicianRepository;
    private final WorkOrderRepository workOrderRepository;
    private final InventoryPartRepository inventoryPartRepository;
    private final ServiceReportRepository serviceReportRepository;

    public DashboardResponse getDashboardSummary() {

        return DashboardResponse.builder()

                // Totals
                .totalCompanies(companyRepository.count())
                .totalCustomers(customerRepository.count())
                .totalSites(siteRepository.count())
                .totalTechnicians(technicianRepository.count())
                .totalWorkOrders(workOrderRepository.count())
                .totalInventoryParts(inventoryPartRepository.count())
                .totalServiceReports(serviceReportRepository.count())

                // Work Orders
                .openWorkOrders(workOrderRepository.countByStatus(WorkOrderStatus.OPEN))
                .assignedWorkOrders(workOrderRepository.countByStatus(WorkOrderStatus.ASSIGNED))
                .inProgressWorkOrders(workOrderRepository.countByStatus(WorkOrderStatus.IN_PROGRESS))
                .completedWorkOrders(workOrderRepository.countByStatus(WorkOrderStatus.COMPLETED))
                .cancelledWorkOrders(workOrderRepository.countByStatus(WorkOrderStatus.CANCELLED))

                // Technicians
                .availableTechnicians(technicianRepository.countByStatus(TechnicianStatus.AVAILABLE))
                .busyTechnicians(technicianRepository.countByStatus(TechnicianStatus.BUSY))
                .offlineTechnicians(technicianRepository.countByStatus(TechnicianStatus.OFFLINE))

                // Inventory
                .lowStockItems(inventoryPartRepository.countByQuantityLessThanEqual(5))
                .outOfStockItems(inventoryPartRepository.countByQuantity(0))

                .build();
    }
}