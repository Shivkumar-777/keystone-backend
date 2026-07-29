package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.TechnicianRequest;
import com.shivkumar.keystonebackend.dto.TechnicianResponse;
import com.shivkumar.keystonebackend.entity.Company;
import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnicianService {

    private final TechnicianRepository technicianRepository;
    private final CompanyRepository companyRepository;

    // ==========================
    // CREATE TECHNICIAN
    // ==========================

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

    // ==========================
    // GET ALL TECHNICIANS
    // ==========================

    public List<TechnicianResponse> getAllTechnicians() {

        return technicianRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // SEARCH BY NAME
    // ==========================

    public Page<TechnicianResponse> searchTechnicians(
            String fullName,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return technicianRepository
                .findByFullNameContainingIgnoreCase(fullName, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY EMAIL
    // ==========================

    public Page<TechnicianResponse> searchByEmail(
            String email,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return technicianRepository
                .findByEmailContainingIgnoreCase(email, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY PHONE
    // ==========================

    public Page<TechnicianResponse> searchByPhone(
            String phone,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return technicianRepository
                .findByPhoneContaining(phone, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // SEARCH BY SPECIALIZATION
    // ==========================

    public Page<TechnicianResponse> searchBySpecialization(
            String specialization,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return technicianRepository
                .findBySpecializationContainingIgnoreCase(specialization, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    public Page<TechnicianResponse> getTechniciansByStatus(
            TechnicianStatus status,
            int page,
            int size
    ) {

        Pageable pageable = PageRequest.of(page, size);

        return technicianRepository
                .findByStatus(status, pageable)
                .map(this::mapToResponse);
    }

    // ==========================
    // GET TECHNICIAN BY ID
    // ==========================

    public TechnicianResponse getTechnicianById(Long id) {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        return mapToResponse(technician);
    }

    // ==========================
    // UPDATE TECHNICIAN
    // ==========================

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

    // ==========================
    // DELETE TECHNICIAN
    // ==========================

    public void deleteTechnician(Long id) {

        Technician technician = technicianRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Technician not found"));

        technicianRepository.delete(technician);
    }

    // ==========================
    // ENTITY -> RESPONSE DTO
    // ==========================

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