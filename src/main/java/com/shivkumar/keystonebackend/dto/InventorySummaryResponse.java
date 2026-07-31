package com.shivkumar.keystonebackend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventorySummaryResponse {

    private long totalParts;

    private long lowStockParts;

    private long outOfStockParts;

}