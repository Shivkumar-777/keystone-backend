package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.DashboardResponse;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import com.shivkumar.keystonebackend.repository.CustomerRepository;
import com.shivkumar.keystonebackend.repository.InventoryPartRepository;
import com.shivkumar.keystonebackend.repository.ServiceReportRepository;
import com.shivkumar.keystonebackend.repository.SiteRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import com.shivkumar.keystonebackend.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    // ==========================
    // DASHBOARD SUMMARY
    // ==========================

    public DashboardResponse getDashboardSummary() {

        return DashboardResponse.builder()

                // ==========================
                // TOTAL COUNTS
                // ==========================

                .totalCompanies(companyRepository.count())
                .totalCustomers(customerRepository.count())
                .totalSites(siteRepository.count())
                .totalTechnicians(technicianRepository.count())
                .totalWorkOrders(workOrderRepository.count())
                .totalInventoryParts(inventoryPartRepository.count())
                .totalServiceReports(serviceReportRepository.count())

                // ==========================
                // WORK ORDER STATUS
                // ==========================

                .openWorkOrders(
                        workOrderRepository.countByStatus(WorkOrderStatus.OPEN)
                )

                .assignedWorkOrders(
                        workOrderRepository.countByStatus(WorkOrderStatus.ASSIGNED)
                )

                .inProgressWorkOrders(
                        workOrderRepository.countByStatus(WorkOrderStatus.IN_PROGRESS)
                )

                .completedWorkOrders(
                        workOrderRepository.countByStatus(WorkOrderStatus.COMPLETED)
                )

                .cancelledWorkOrders(
                        workOrderRepository.countByStatus(WorkOrderStatus.CANCELLED)
                )

                .overdueWorkOrders(
                        workOrderRepository.countBySlaDueDateBeforeAndStatusNot(
                                LocalDateTime.now(),
                                WorkOrderStatus.COMPLETED
                        )
                )

                // ==========================
                // TECHNICIAN STATUS
                // ==========================

                .availableTechnicians(
                        technicianRepository.countByStatus(
                                TechnicianStatus.AVAILABLE
                        )
                )

                .busyTechnicians(
                        technicianRepository.countByStatus(
                                TechnicianStatus.BUSY
                        )
                )

                .offlineTechnicians(
                        technicianRepository.countByStatus(
                                TechnicianStatus.OFFLINE
                        )
                )

                // ==========================
                // INVENTORY
                // ==========================

                .lowStockItems(
                        inventoryPartRepository.countLowStockParts()
                )

                .outOfStockItems(
                        inventoryPartRepository.countByQuantity(0)
                )

                .build();
    }
}