package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.ServiceAttachment;
import com.shivkumar.keystonebackend.entity.ServiceReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ServiceAttachmentRepository extends JpaRepository<ServiceAttachment, Long> {

    List<ServiceAttachment> findByServiceReport(ServiceReport serviceReport);

    List<ServiceAttachment> findByServiceReportId(Long serviceReportId);

    Optional<ServiceAttachment> findByFileName(String fileName);

    boolean existsByFileName(String fileName);

    void deleteByFileName(String fileName);
}