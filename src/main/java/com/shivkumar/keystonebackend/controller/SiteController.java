package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.SiteRequest;
import com.shivkumar.keystonebackend.dto.SiteResponse;
import com.shivkumar.keystonebackend.service.SiteService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ADMIN','TECHNICIAN')")
public class SiteController {

    private final SiteService siteService;

    // ==========================
    // CREATE
    // ==========================

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SiteResponse createSite(
            @Valid @RequestBody SiteRequest request) {

        return siteService.createSite(request);
    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public List<SiteResponse> getAllSites() {

        return siteService.getAllSites();
    }

    // ==========================
    // SEARCH BY SITE NAME
    // ==========================

    @GetMapping("/search")
    public Page<SiteResponse> searchSites(
            @RequestParam String siteName,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return siteService.searchSites(siteName, page, size);
    }

    // ==========================
    // SEARCH BY CITY
    // ==========================

    @GetMapping("/search/city")
    public Page<SiteResponse> searchByCity(
            @RequestParam String city,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return siteService.searchByCity(city, page, size);
    }

    // ==========================
    // SEARCH BY STATE
    // ==========================

    @GetMapping("/search/state")
    public Page<SiteResponse> searchByState(
            @RequestParam String state,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return siteService.searchByState(state, page, size);
    }

    // ==========================
    // SEARCH BY POSTAL CODE
    // ==========================

    @GetMapping("/search/postal-code")
    public Page<SiteResponse> searchByPostalCode(
            @RequestParam String postalCode,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return siteService.searchByPostalCode(postalCode, page, size);
    }

    // ==========================
    // FILTER BY CUSTOMER
    // ==========================

    @GetMapping("/customer/{customerId}")
    public Page<SiteResponse> getSitesByCustomer(
            @PathVariable Long customerId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {

        return siteService.getSitesByCustomer(customerId, page, size);
    }

    // ==========================
    // GET BY ID
    // ==========================

    @GetMapping("/{id}")
    public SiteResponse getSiteById(
            @PathVariable Long id) {

        return siteService.getSiteById(id);
    }

    // ==========================
    // UPDATE
    // ==========================

    @PutMapping("/{id}")
    public SiteResponse updateSite(
            @PathVariable Long id,
            @Valid @RequestBody SiteRequest request) {

        return siteService.updateSite(id, request);
    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSite(
            @PathVariable Long id) {

        siteService.deleteSite(id);
    }
}