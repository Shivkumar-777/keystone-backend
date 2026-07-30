package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.CompanyRequest;
import com.shivkumar.keystonebackend.dto.CompanyResponse;
import com.shivkumar.keystonebackend.service.CompanyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyService companyService;

    // Create Company
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CompanyResponse createCompany(@Valid @RequestBody CompanyRequest request) {
        return companyService.createCompany(request);
    }

    // Get All Companies
    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    // Get Company By ID
    @GetMapping("/{id}")
    public CompanyResponse getCompanyById(@PathVariable Long id) {
        return companyService.getCompanyById(id);
    }

    // Update Company
    @PutMapping("/{id}")
    public CompanyResponse updateCompany(
            @PathVariable Long id,
            @Valid @RequestBody CompanyRequest request) {

        return companyService.updateCompany(id, request);
    }

    // Delete Company
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
    }
}