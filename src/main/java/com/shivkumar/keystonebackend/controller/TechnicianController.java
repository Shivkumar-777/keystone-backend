package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.TechnicianRequest;
import com.shivkumar.keystonebackend.dto.TechnicianResponse;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import com.shivkumar.keystonebackend.service.TechnicianService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class TechnicianController {

    private final TechnicianService technicianService;

    // ==========================
    // CREATE TECHNICIAN
    // ==========================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicianResponse createTechnician(
            @Valid @RequestBody TechnicianRequest request) {

        return technicianService.createTechnician(request);
    }

    // ==========================
    // GET ALL TECHNICIANS
    // ==========================

    @GetMapping
    public List<TechnicianResponse> getAllTechnicians() {

        return technicianService.getAllTechnicians();
    }

    // ==========================
    // SEARCH BY NAME
    // ==========================

    @GetMapping("/search")
    public Page<TechnicianResponse> searchTechnicians(
            @RequestParam String fullName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return technicianService.searchTechnicians(fullName, page, size);
    }

    // ==========================
    // SEARCH BY EMAIL
    // ==========================

    @GetMapping("/search/email")
    public Page<TechnicianResponse> searchByEmail(
            @RequestParam String email,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return technicianService.searchByEmail(email, page, size);
    }

    // ==========================
    // SEARCH BY PHONE
    // ==========================

    @GetMapping("/search/phone")
    public Page<TechnicianResponse> searchByPhone(
            @RequestParam String phone,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return technicianService.searchByPhone(phone, page, size);
    }

    // ==========================
    // SEARCH BY SPECIALIZATION
    // ==========================

    @GetMapping("/search/specialization")
    public Page<TechnicianResponse> searchBySpecialization(
            @RequestParam String specialization,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return technicianService.searchBySpecialization(
                specialization,
                page,
                size
        );
    }

    // ==========================
    // FILTER BY STATUS
    // ==========================

    @GetMapping("/status/{status}")
    public Page<TechnicianResponse> getByStatus(
            @PathVariable TechnicianStatus status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return technicianService.getTechniciansByStatus(
                status,
                page,
                size
        );
    }

    // ==========================
    // GET TECHNICIAN BY ID
    // ==========================

    @GetMapping("/{id}")
    public TechnicianResponse getTechnicianById(
            @PathVariable Long id) {

        return technicianService.getTechnicianById(id);
    }

    // ==========================
    // UPDATE TECHNICIAN
    // ==========================

    @PutMapping("/{id}")
    public TechnicianResponse updateTechnician(
            @PathVariable Long id,
            @Valid @RequestBody TechnicianRequest request) {

        return technicianService.updateTechnician(id, request);
    }

    // ==========================
    // DELETE TECHNICIAN
    // ==========================

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTechnician(
            @PathVariable Long id) {

        technicianService.deleteTechnician(id);
    }
}