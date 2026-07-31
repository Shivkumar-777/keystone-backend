package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.InventoryPartRequest;
import com.shivkumar.keystonebackend.dto.InventoryPartResponse;
import com.shivkumar.keystonebackend.service.InventoryPartService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory-parts")
@RequiredArgsConstructor
@PreAuthorize("hasRole('ADMIN')")
public class InventoryPartController {

    private final InventoryPartService inventoryPartService;

    // ==========================
    // CREATE
    // ==========================

    @PostMapping
    public InventoryPartResponse createInventoryPart(
            @Valid @RequestBody InventoryPartRequest request) {

        return inventoryPartService.createInventoryPart(request);
    }

    // ==========================
    // GET ALL
    // ==========================

    @GetMapping
    public List<InventoryPartResponse> getAllInventoryParts() {

        return inventoryPartService.getAllInventoryParts();
    }

    // ==========================
    // GET BY ID
    // ==========================

    @GetMapping("/{id}")
    public InventoryPartResponse getInventoryPartById(
            @PathVariable Long id) {

        return inventoryPartService.getInventoryPartById(id);
    }

    // ==========================
    // UPDATE
    // ==========================

    @PutMapping("/{id}")
    public InventoryPartResponse updateInventoryPart(
            @PathVariable Long id,
            @Valid @RequestBody InventoryPartRequest request) {

        return inventoryPartService.updateInventoryPart(id, request);
    }

    // ==========================
    // DELETE
    // ==========================

    @DeleteMapping("/{id}")
    public String deleteInventoryPart(
            @PathVariable Long id) {

        inventoryPartService.deleteInventoryPart(id);

        return "Inventory Part deleted successfully";
    }
}