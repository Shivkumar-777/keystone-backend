package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.ServiceReportRequest;
import com.shivkumar.keystonebackend.dto.ServiceReportResponse;
import com.shivkumar.keystonebackend.service.ServiceReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/service-reports")
@RequiredArgsConstructor
public class ServiceReportController {

    private final ServiceReportService serviceReportService;

    // CREATE
    @PostMapping
    public ServiceReportResponse createServiceReport(
            @RequestBody ServiceReportRequest request) {

        return serviceReportService.createServiceReport(request);
    }

    // GET ALL
    @GetMapping
    public List<ServiceReportResponse> getAllServiceReports() {

        return serviceReportService.getAllServiceReports();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ServiceReportResponse getServiceReportById(
            @PathVariable Long id) {

        return serviceReportService.getServiceReportById(id);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ServiceReportResponse updateServiceReport(
            @PathVariable Long id,
            @RequestBody ServiceReportRequest request) {

        return serviceReportService.updateServiceReport(id, request);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteServiceReport(
            @PathVariable Long id) {

        serviceReportService.deleteServiceReport(id);

        return "Service Report deleted successfully";
    }
}