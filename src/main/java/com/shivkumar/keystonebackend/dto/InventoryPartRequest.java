package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.PartCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryPartRequest {

    private String partName;

    private String partCode;

    private PartCategory category;

    private Integer quantity;

    private Integer minimumStock;

    private BigDecimal price;

    private String unit;

    private Long companyId;
}