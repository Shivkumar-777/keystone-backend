package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.WorkOrderRequest;
import com.shivkumar.keystonebackend.dto.WorkOrderResponse;
import com.shivkumar.keystonebackend.entity.WorkOrderPriority;
import com.shivkumar.keystonebackend.entity.WorkOrderStatus;
import com.shivkumar.keystonebackend.service.WorkOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/work-orders")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','TECHNICIAN')")
public class WorkOrderController {

    private final WorkOrderService workOrderService;

    // ==========================
    // CREATE
    // ==========================

    @PostMapping
    public WorkOrderResponse createWorkOrder(
            @Valid @RequestBody WorkOrderRequest request) {

        return workOrderService.createWorkOrder(request);
    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public List<WorkOrderResponse> getAllWorkOrders() {

        return workOrderService.getAllWorkOrders();
    }

    // ==========================
    // SEARCH BY TITLE
    // ==========================

    @GetMapping("/search")
    public Page<WorkOrderResponse> searchWorkOrders(
            @RequestParam String title,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.searchWorkOrders(title, page, size);
    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    @GetMapping("/status/{status}")
    public Page<WorkOrderResponse> getByStatus(
            @PathVariable WorkOrderStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.getWorkOrdersByStatus(status, page, size);
    }

    // ==========================
    // FILTER BY PRIORITY
    // ==========================

    @GetMapping("/priority/{priority}")
    public Page<WorkOrderResponse> getByPriority(
            @PathVariable WorkOrderPriority priority,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.getWorkOrdersByPriority(priority, page, size);
    }

    // ==========================
    // FILTER BY CUSTOMER
    // ==========================

    @GetMapping("/customer/{customerId}")
    public Page<WorkOrderResponse> getByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.getWorkOrdersByCustomer(customerId, page, size);
    }

    // ==========================
    // FILTER BY TECHNICIAN
    // ==========================

    @GetMapping("/technician/{technicianId}")
    public Page<WorkOrderResponse> getByTechnician(
            @PathVariable Long technicianId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.getWorkOrdersByTechnician(technicianId, page, size);
    }

    // ==========================
    // FILTER BY SITE
    // ==========================

    @GetMapping("/site/{siteId}")
    public Page<WorkOrderResponse> getBySite(
            @PathVariable Long siteId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return workOrderService.getWorkOrdersBySite(siteId, page, size);
    }

    // ==========================
    // GET BY ID
    // ==========================

    @GetMapping("/{id}")
    public WorkOrderResponse getWorkOrderById(
            @PathVariable Long id) {

        return workOrderService.getWorkOrderById(id);
    }

    // ==========================
    // UPDATE
    // ==========================

    @PutMapping("/{id}")
    public WorkOrderResponse updateWorkOrder(
            @PathVariable Long id,
            @Valid @RequestBody WorkOrderRequest request) {

        return workOrderService.updateWorkOrder(id, request);
    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public String deleteWorkOrder(
            @PathVariable Long id) {

        workOrderService.deleteWorkOrder(id);

        return "Work Order deleted successfully";
    }
}