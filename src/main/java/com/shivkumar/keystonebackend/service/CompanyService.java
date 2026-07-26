package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.CompanyRequest;
import com.shivkumar.keystonebackend.dto.CompanyResponse;
import com.shivkumar.keystonebackend.entity.Company;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    // Create Company
    public CompanyResponse createCompany(CompanyRequest request) {

        Company company = Company.builder()
                .companyName(request.getCompanyName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .address(request.getAddress())
                .website(request.getWebsite())
                .build();

        Company savedCompany = companyRepository.save(company);

        return mapToResponse(savedCompany);
    }

    // Get All Companies
    public List<CompanyResponse> getAllCompanies() {

        return companyRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Get Company By ID
    public CompanyResponse getCompanyById(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        return mapToResponse(company);
    }

    // Update Company
    public CompanyResponse updateCompany(Long id, CompanyRequest request) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        company.setCompanyName(request.getCompanyName());
        company.setEmail(request.getEmail());
        company.setPhone(request.getPhone());
        company.setAddress(request.getAddress());
        company.setWebsite(request.getWebsite());

        Company updatedCompany = companyRepository.save(company);

        return mapToResponse(updatedCompany);
    }

    // Delete Company
    public void deleteCompany(Long id) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        companyRepository.delete(company);
    }

    // Entity -> DTO Mapper
    private CompanyResponse mapToResponse(Company company) {
        return CompanyResponse.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .email(company.getEmail())
                .phone(company.getPhone())
                .address(company.getAddress())
                .website(company.getWebsite())
                .build();
    }
}