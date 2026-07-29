package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.TechnicianRequest;
import com.shivkumar.keystonebackend.dto.TechnicianResponse;
import com.shivkumar.keystonebackend.entity.Company;
import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final CompanyRepository companyRepository;

    // Create Technician
    public TechnicianResponse createTechnician(TechnicianRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Technician technician = Technician.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .specialization(request.getSpecialization())
                .experience(request.getExperience())
                .status(TechnicianStatus.AVAILABLE)
                .company(company)
                .build();

        Technician savedTechnician = technicianRepository.save(technician);

        return mapToResponse(savedTechnician);
    }

    // Get All Technicians
    public List<TechnicianResponse> getAllTechnicians() {

        return technicianRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Technician By ID
    public TechnicianResponse getTechnicianById(Long id) {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        return mapToResponse(technician);
    }

    // Update Technician
    public TechnicianResponse updateTechnician(Long id, TechnicianRequest request) {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        technician.setFullName(request.getFullName());
        technician.setEmail(request.getEmail());
        technician.setPhone(request.getPhone());
        technician.setSpecialization(request.getSpecialization());
        technician.setExperience(request.getExperience());
        technician.setCompany(company);

        Technician updatedTechnician = technicianRepository.save(technician);

        return mapToResponse(updatedTechnician);
    }

    // Delete Technician
    public void deleteTechnician(Long id) {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        technicianRepository.delete(technician);
    }

    // Entity -> DTO Mapper
    private TechnicianResponse mapToResponse(Technician technician) {

        return TechnicianResponse.builder()
                .id(technician.getId())
                .fullName(technician.getFullName())
                .email(technician.getEmail())
                .phone(technician.getPhone())
                .specialization(technician.getSpecialization())
                .experience(technician.getExperience())
                .status(technician.getStatus())
                .companyId(technician.getCompany().getId())
                .companyName(technician.getCompany().getCompanyName())
                .build();
    }
}