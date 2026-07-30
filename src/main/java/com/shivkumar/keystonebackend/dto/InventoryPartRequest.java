package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.entity.PartCategory;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;
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

    @NotBlank(message = "Part name is required")
    @Size(min = 2, max = 100, message = "Part name must be between 2 and 100 characters")
    private String partName;

    @NotBlank(message = "Part code is required")
    @Size(min = 2, max = 50, message = "Part code must be between 2 and 50 characters")
    private String partCode;

    @NotNull(message = "Part category is required")
    private PartCategory category;

    @NotNull(message = "Quantity is required")
    @PositiveOrZero(message = "Quantity cannot be negative")
    private Integer quantity;

    @NotNull(message = "Minimum stock is required")
    @PositiveOrZero(message = "Minimum stock cannot be negative")
    private Integer minimumStock;

    @NotNull(message = "Price is required")
    @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0")
    private BigDecimal price;

    @NotBlank(message = "Unit is required")
    @Size(max = 20, message = "Unit cannot exceed 20 characters")
    private String unit;

    @NotNull(message = "Company ID is required")
    private Long companyId;
}