package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.InventoryPartRequest;
import com.shivkumar.keystonebackend.dto.InventoryPartResponse;
import com.shivkumar.keystonebackend.entity.Company;
import com.shivkumar.keystonebackend.entity.InventoryPart;
import com.shivkumar.keystonebackend.repository.CompanyRepository;
import com.shivkumar.keystonebackend.repository.InventoryPartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryPartService {

    private final InventoryPartRepository inventoryPartRepository;
    private final CompanyRepository companyRepository;

    // ==========================
    // CREATE
    // ==========================

    public InventoryPartResponse createInventoryPart(InventoryPartRequest request) {

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        InventoryPart part = InventoryPart.builder()
                .partName(request.getPartName())
                .partCode(request.getPartCode())
                .category(request.getCategory())
                .quantity(request.getQuantity())
                .minimumStock(request.getMinimumStock())
                .price(request.getPrice())
                .unit(request.getUnit())
                .company(company)
                .build();

        return mapToResponse(inventoryPartRepository.save(part));
    }

    // ==========================
    // GET ALL
    // ==========================

    @Transactional(readOnly = true)
    public List<InventoryPartResponse> getAllInventoryParts() {

        return inventoryPartRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // ==========================
    // GET BY ID
    // ==========================

    @Transactional(readOnly = true)
    public InventoryPartResponse getInventoryPartById(Long id) {

        InventoryPart part = inventoryPartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory Part not found"));

        return mapToResponse(part);
    }

    // ==========================
    // UPDATE
    // ==========================

    public InventoryPartResponse updateInventoryPart(Long id, InventoryPartRequest request) {

        InventoryPart part = inventoryPartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory Part not found"));

        Company company = companyRepository.findById(request.getCompanyId())
                .orElseThrow(() -> new RuntimeException("Company not found"));

        part.setPartName(request.getPartName());
        part.setPartCode(request.getPartCode());
        part.setCategory(request.getCategory());
        part.setQuantity(request.getQuantity());
        part.setMinimumStock(request.getMinimumStock());
        part.setPrice(request.getPrice());
        part.setUnit(request.getUnit());
        part.setCompany(company);

        return mapToResponse(inventoryPartRepository.save(part));
    }

    // ==========================
    // DELETE
    // ==========================

    public void deleteInventoryPart(Long id) {

        InventoryPart part = inventoryPartRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Inventory Part not found"));

        inventoryPartRepository.delete(part);
    }

    // ==========================
    // ENTITY → RESPONSE DTO
    // ==========================

    private InventoryPartResponse mapToResponse(InventoryPart part) {

        boolean lowStock = part.getQuantity() <= part.getMinimumStock();

        return InventoryPartResponse.builder()
                .id(part.getId())
                .partName(part.getPartName())
                .partCode(part.getPartCode())
                .category(part.getCategory())
                .quantity(part.getQuantity())
                .minimumStock(part.getMinimumStock())
                .price(part.getPrice())
                .unit(part.getUnit())
                .createdAt(part.getCreatedAt())
                .companyId(part.getCompany().getId())
                .companyName(part.getCompany().getCompanyName())
                .lowStock(lowStock)
                .build();
    }
}