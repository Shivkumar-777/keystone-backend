package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.DashboardResponse;
import com.shivkumar.keystonebackend.service.DashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard", description = "Dashboard Summary APIs")
public class DashboardController {

    private final DashboardService dashboardService;

    /**
     * Dashboard summary
     */
    @Operation(summary = "Get Dashboard Summary")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    @GetMapping
    public DashboardResponse getDashboardSummary() {

        return dashboardService.getDashboardSummary();
    }
}