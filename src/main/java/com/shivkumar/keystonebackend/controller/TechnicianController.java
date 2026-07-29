package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.TechnicianRequest;
import com.shivkumar.keystonebackend.dto.TechnicianResponse;
import com.shivkumar.keystonebackend.service.TechnicianService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technicians")
@RequiredArgsConstructor
public class TechnicianController {

    private final TechnicianService technicianService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnicianResponse createTechnician(@RequestBody TechnicianRequest request) {
        return technicianService.createTechnician(request);
    }

    @GetMapping
    public List<TechnicianResponse> getAllTechnicians() {
        return technicianService.getAllTechnicians();
    }

    @GetMapping("/{id}")
    public TechnicianResponse getTechnicianById(@PathVariable Long id) {
        return technicianService.getTechnicianById(id);
    }

    @PutMapping("/{id}")
    public TechnicianResponse updateTechnician(
            @PathVariable Long id,
            @RequestBody TechnicianRequest request) {

        return technicianService.updateTechnician(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTechnician(@PathVariable Long id) {
        technicianService.deleteTechnician(id);
    }
}