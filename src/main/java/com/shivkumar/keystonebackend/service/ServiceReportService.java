package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.ServiceReportRequest;
import com.shivkumar.keystonebackend.dto.ServiceReportResponse;
import com.shivkumar.keystonebackend.entity.ServiceReport;
import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.entity.WorkOrder;
import com.shivkumar.keystonebackend.repository.ServiceReportRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import com.shivkumar.keystonebackend.repository.WorkOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServiceReportService {

    private final ServiceReportRepository serviceReportRepository;
    private final WorkOrderRepository workOrderRepository;
    private final TechnicianRepository technicianRepository;

    // CREATE
    public ServiceReportResponse createServiceReport(ServiceReportRequest request) {

        WorkOrder workOrder = workOrderRepository.findById(request.getWorkOrderId())
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        Technician technician = technicianRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        ServiceReport report = ServiceReport.builder()
                .workPerformed(request.getWorkPerformed())
                .technicianNotes(request.getTechnicianNotes())
                .customerFeedback(request.getCustomerFeedback())
                .hoursWorked(request.getHoursWorked())
                .status(request.getStatus())
                .workOrder(workOrder)
                .technician(technician)
                .build();

        return mapToResponse(serviceReportRepository.save(report));
    }

    // GET ALL
    public List<ServiceReportResponse> getAllServiceReports() {

        return serviceReportRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // GET BY ID
    public ServiceReportResponse getServiceReportById(Long id) {

        ServiceReport report = serviceReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Report not found"));

        return mapToResponse(report);
    }

    // UPDATE
    public ServiceReportResponse updateServiceReport(Long id, ServiceReportRequest request) {

        ServiceReport report = serviceReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Report not found"));

        WorkOrder workOrder = workOrderRepository.findById(request.getWorkOrderId())
                .orElseThrow(() -> new RuntimeException("Work Order not found"));

        Technician technician = technicianRepository.findById(request.getTechnicianId())
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        report.setWorkPerformed(request.getWorkPerformed());
        report.setTechnicianNotes(request.getTechnicianNotes());
        report.setCustomerFeedback(request.getCustomerFeedback());
        report.setHoursWorked(request.getHoursWorked());
        report.setStatus(request.getStatus());
        report.setWorkOrder(workOrder);
        report.setTechnician(technician);

        return mapToResponse(serviceReportRepository.save(report));
    }

    // DELETE
    public void deleteServiceReport(Long id) {

        ServiceReport report = serviceReportRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service Report not found"));

        serviceReportRepository.delete(report);
    }

    // ENTITY → RESPONSE DTO
    private ServiceReportResponse mapToResponse(ServiceReport report) {

        return ServiceReportResponse.builder()
                .id(report.getId())
                .workPerformed(report.getWorkPerformed())
                .technicianNotes(report.getTechnicianNotes())
                .customerFeedback(report.getCustomerFeedback())
                .hoursWorked(report.getHoursWorked())
                .status(report.getStatus())
                .reportDate(report.getReportDate())

                .workOrderId(report.getWorkOrder().getId())
                .workOrderTitle(report.getWorkOrder().getTitle())

                .technicianId(report.getTechnician().getId())
                .technicianName(report.getTechnician().getFullName())

                .build();
    }
}