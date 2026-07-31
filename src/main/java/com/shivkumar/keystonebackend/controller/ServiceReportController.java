package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.ServiceReportRequest;
import com.shivkumar.keystonebackend.dto.ServiceReportResponse;
import com.shivkumar.keystonebackend.entity.ServiceStatus;
import com.shivkumar.keystonebackend.service.ServiceReportService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/service-reports")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','TECHNICIAN')")
public class ServiceReportController {

    private final ServiceReportService serviceReportService;

    // ==========================
    // CREATE
    // ==========================

    @PostMapping
    public ServiceReportResponse createServiceReport(
            @Valid @RequestBody ServiceReportRequest request) {

        return serviceReportService.createServiceReport(request);
    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public List<ServiceReportResponse> getAllServiceReports() {

        return serviceReportService.getAllServiceReports();
    }

    // ==========================
    // SEARCH WORK PERFORMED
    // ==========================

    @GetMapping("/search/work")
    public Page<ServiceReportResponse> searchWorkPerformed(
            @RequestParam String workPerformed,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.searchWorkPerformed(
                workPerformed,
                page,
                size
        );
    }

    // ==========================
    // SEARCH TECHNICIAN NOTES
    // ==========================

    @GetMapping("/search/notes")
    public Page<ServiceReportResponse> searchTechnicianNotes(
            @RequestParam String technicianNotes,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.searchTechnicianNotes(
                technicianNotes,
                page,
                size
        );
    }

    // ==========================
    // SEARCH CUSTOMER FEEDBACK
    // ==========================

    @GetMapping("/search/feedback")
    public Page<ServiceReportResponse> searchCustomerFeedback(
            @RequestParam String customerFeedback,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.searchCustomerFeedback(
                customerFeedback,
                page,
                size
        );
    }

    // ==========================
    // FILTER BY WORK ORDER
    // ==========================

    @GetMapping("/work-order/{workOrderId}")
    public Page<ServiceReportResponse> getReportsByWorkOrder(
            @PathVariable Long workOrderId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.getReportsByWorkOrder(
                workOrderId,
                page,
                size
        );
    }

    // ==========================
    // FILTER BY TECHNICIAN
    // ==========================

    @GetMapping("/technician/{technicianId}")
    public Page<ServiceReportResponse> getReportsByTechnician(
            @PathVariable Long technicianId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.getReportsByTechnician(
                technicianId,
                page,
                size
        );
    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    @GetMapping("/status/{status}")
    public Page<ServiceReportResponse> getReportsByStatus(
            @PathVariable ServiceStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.getReportsByStatus(
                status,
                page,
                size
        );
    }

    // ==========================
    // FILTER BY DATE RANGE
    // ==========================

    @GetMapping("/date-range")
    public Page<ServiceReportResponse> getReportsByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return serviceReportService.getReportsByDateRange(
                start,
                end,
                page,
                size
        );
    }

    // ==========================
    // GET BY ID
    // ==========================

    @GetMapping("/{id}")
    public ServiceReportResponse getServiceReportById(
            @PathVariable Long id) {

        return serviceReportService.getServiceReportById(id);
    }

    // ==========================
    // UPDATE
    // ==========================

    @PutMapping("/{id}")
    public ServiceReportResponse updateServiceReport(
            @PathVariable Long id,
            @Valid @RequestBody ServiceReportRequest request) {

        return serviceReportService.updateServiceReport(id, request);
    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public String deleteServiceReport(
            @PathVariable Long id) {

        serviceReportService.deleteServiceReport(id);

        return "Service Report deleted successfully";
    }
}