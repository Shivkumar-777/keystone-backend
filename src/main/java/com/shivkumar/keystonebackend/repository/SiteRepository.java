package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Site;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SiteRepository extends JpaRepository<Site, Long> {
}