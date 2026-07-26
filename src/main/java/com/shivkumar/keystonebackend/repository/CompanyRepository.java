package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {

}