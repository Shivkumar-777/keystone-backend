package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.DashboardResponse;
import com.shivkumar.keystonebackend.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping
    public DashboardResponse getDashboardSummary() {
        return dashboardService.getDashboardSummary();
    }
}