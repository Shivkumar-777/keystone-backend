package com.shivkumar.keystonebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "inventory_parts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class InventoryPart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String partName;

    @Column(unique = true)
    private String partCode;

    @Enumerated(EnumType.STRING)
    private PartCategory category;

    private Integer quantity;

    private Integer minimumStock;

    private BigDecimal price;

    private String unit;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();
    }
}