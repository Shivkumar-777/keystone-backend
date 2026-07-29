package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.SiteRequest;
import com.shivkumar.keystonebackend.dto.SiteResponse;
import com.shivkumar.keystonebackend.service.SiteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sites")
@RequiredArgsConstructor
public class SiteController {

    private final SiteService siteService;

    // Create Site
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SiteResponse createSite(@RequestBody SiteRequest request) {
        return siteService.createSite(request);
    }

    // Get All Sites
    @GetMapping
    public List<SiteResponse> getAllSites() {
        return siteService.getAllSites();
    }

    // Get Site By ID
    @GetMapping("/{id}")
    public SiteResponse getSiteById(@PathVariable Long id) {
        return siteService.getSiteById(id);
    }

    // Update Site
    @PutMapping("/{id}")
    public SiteResponse updateSite(
            @PathVariable Long id,
            @RequestBody SiteRequest request) {

        return siteService.updateSite(id, request);
    }

    // Delete Site
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteSite(@PathVariable Long id) {
        siteService.deleteSite(id);
    }
}