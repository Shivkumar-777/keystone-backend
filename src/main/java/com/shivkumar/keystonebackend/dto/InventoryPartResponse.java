package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.PartCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryPartResponse {

    private Long id;

    private String partName;

    private String partCode;

    private PartCategory category;

    private Integer quantity;

    private Integer minimumStock;

    private BigDecimal price;

    private String unit;

    private LocalDateTime createdAt;

    private Long companyId;

    private String companyName;

    // Indicates whether stock is below the minimum threshold
    private Boolean lowStock;
}