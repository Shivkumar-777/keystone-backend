package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.WorkOrderRequest;
import com.shivkumar.keystonebackend.dto.WorkOrderResponse;
import com.shivkumar.keystonebackend.entity.*;
import com.shivkumar.keystonebackend.enums.NotificationType;
import com.shivkumar.keystonebackend.enums.SLAStatus;
import com.shivkumar.keystonebackend.repository.CustomerRepository;
import com.shivkumar.keystonebackend.repository.SiteRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import com.shivkumar.keystonebackend.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class WorkOrderService {

    private final WorkOrderRepository workOrderRepository;
    private final CustomerRepository customerRepository;
    private final SiteRepository siteRepository;
    private final TechnicianRepository technicianRepository;
    private final NotificationService notificationService;

    // ==========================
    // CREATE
    // ==========================

    public WorkOrderResponse createWorkOrder(WorkOrderRequest request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() -> new RuntimeException("Site not found"));

        Technician technician = technicianRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        LocalDateTime slaDueDate = calculateSlaDueDate(request.getPriority());

        WorkOrder workOrder = WorkOrder.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .scheduledDate(request.getScheduledDate())
                .completedDate(request.getCompletedDate())
                .slaDueDate(slaDueDate)
                .slaStatus(SLAStatus.ON_TIME)
                .customer(customer)
                .site(site)
                .technician(technician)
                .build();

        WorkOrder savedWorkOrder = workOrderRepository.save(workOrder);

        notificationService.createNotification(
                technician,
                NotificationType.WORK_ORDER_ASSIGNED,
                "New Work Order Assigned",
                "You have been assigned Work Order #" + savedWorkOrder.getId()
        );

        return mapToResponse(savedWorkOrder);
    }

    // ==========================
    // GET ALL
    // ==========================

    @Transactional(readOnly = true)
    public List<WorkOrderResponse> getAllWorkOrders() {

        return workOrderRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // SEARCH BY TITLE
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> searchWorkOrders(
            String title,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findByTitleContainingIgnoreCase(title, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> getWorkOrdersByStatus(
            WorkOrderStatus status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findByStatus(status, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY PRIORITY
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> getWorkOrdersByPriority(
            WorkOrderPriority priority,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findByPriority(priority, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY CUSTOMER
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> getWorkOrdersByCustomer(
            Long customerId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findByCustomerId(customerId, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY TECHNICIAN
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> getWorkOrdersByTechnician(
            Long technicianId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findByTechnicianId(technicianId, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY SITE
    // ==========================

    @Transactional(readOnly = true)
    public Page<WorkOrderResponse> getWorkOrdersBySite(
            Long siteId,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return workOrderRepository
                .findBySiteId(siteId, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // GET BY ID
    // ==========================

    @Transactional(readOnly = true)
    public WorkOrderResponse getWorkOrderById(Long id) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        return mapToResponse(workOrder);
    }

    // ==========================
    // UPDATE
    // ==========================

    public WorkOrderResponse updateWorkOrder(Long id, WorkOrderRequest request) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Site site = siteRepository.findById(request.getSiteId())
                .orElseThrow(() -> new RuntimeException("Site not found"));

        Technician technician = technicianRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        boolean technicianChanged =
                !workOrder.getTechnician().getId().equals(technician.getId());

        boolean completedNow =
                workOrder.getStatus() != WorkOrderStatus.COMPLETED
                        && request.getStatus() == WorkOrderStatus.COMPLETED;

        workOrder.setTitle(request.getTitle());
        workOrder.setDescription(request.getDescription());
        workOrder.setPriority(request.getPriority());
        workOrder.setStatus(request.getStatus());
        workOrder.setScheduledDate(request.getScheduledDate());
        workOrder.setCompletedDate(request.getCompletedDate());
        workOrder.setCustomer(customer);
        workOrder.setSite(site);
        workOrder.setTechnician(technician);

        WorkOrder updatedWorkOrder = workOrderRepository.save(workOrder);

        // Notify reassigned technician
        if (technicianChanged) {

            notificationService.createNotification(
                    technician,
                    NotificationType.WORK_ORDER_ASSIGNED,
                    "Work Order Assigned",
                    "Work Order #" + updatedWorkOrder.getId()
                            + " has been assigned to you."
            );
        }

        // Notify completion
        if (completedNow) {

            notificationService.createNotification(
                    technician,
                    NotificationType.WORK_ORDER_COMPLETED,
                    "Work Order Completed",
                    "Work Order #" + updatedWorkOrder.getId()
                            + " has been marked as completed."
            );
        }

        return mapToResponse(updatedWorkOrder);
    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteWorkOrder(Long id) {

        WorkOrder workOrder = workOrderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        workOrderRepository.delete(workOrder);
    }

    // ==========================
    // CALCULATE SLA DUE DATE
    // ==========================

    private LocalDateTime calculateSlaDueDate(WorkOrderPriority priority) {

        LocalDateTime now = LocalDateTime.now();

        return switch (priority) {

            case LOW -> now.plusHours(72);

            case MEDIUM -> now.plusHours(24);

            case HIGH -> now.plusHours(8);

            case CRITICAL -> now.plusHours(4);
        };
    }

    // ==========================
    // ENTITY → RESPONSE DTO
    // ==========================

    private WorkOrderResponse mapToResponse(WorkOrder workOrder) {

        return WorkOrderResponse.builder()
                .id(workOrder.getId())
                .title(workOrder.getTitle())
                .description(workOrder.getDescription())
                .priority(workOrder.getPriority())
                .status(workOrder.getStatus())
                .scheduledDate(workOrder.getScheduledDate())
                .completedDate(workOrder.getCompletedDate())
                .createdAt(workOrder.getCreatedAt())

                .customerId(workOrder.getCustomer().getId())
                .customerName(workOrder.getCustomer().getCustomerName())

                .siteId(workOrder.getSite().getId())
                .siteName(workOrder.getSite().getSiteName())

                .technicianId(workOrder.getTechnician().getId())
                .technicianName(workOrder.getTechnician().getFullName())

                .build();
    }
}