package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.InventoryPart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryPartRepository extends JpaRepository<InventoryPart, Long> {

    long countByQuantityLessThanEqual(Integer quantity);

    long countByQuantity(Integer quantity);

}