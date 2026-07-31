package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.InventoryPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryPartRepository extends JpaRepository<InventoryPart, Long> {

    // ==========================
    // Dashboard
    // ==========================

    @Query("""
            SELECT COUNT(i)
            FROM InventoryPart i
            WHERE i.quantity <= i.minimumStock
            """)
    long countLowStockParts();

    // Optional
    long countByQuantity(Integer quantity);

}