package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TechnicianRepository extends JpaRepository<Technician, Long> {

    long countByStatus(TechnicianStatus status);

}