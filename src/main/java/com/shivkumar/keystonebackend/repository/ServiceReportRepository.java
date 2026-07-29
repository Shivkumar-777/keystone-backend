package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceReportRepository extends JpaRepository<ServiceReport, Long> {
}