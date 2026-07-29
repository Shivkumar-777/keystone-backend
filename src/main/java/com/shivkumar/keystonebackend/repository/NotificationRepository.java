package com.shivkumar.keystonebackend.repository;

import com.shivkumar.keystonebackend.entity.Notification;
import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.NotificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    /**
     * Get all notifications of a technician (latest first)
     */
    List<Notification> findByTechnicianOrderByCreatedAtDesc(
            Technician technician
    );

    /**
     * Get unread notifications
     */
    List<Notification> findByTechnicianAndStatusOrderByCreatedAtDesc(
            Technician technician,
            NotificationStatus status
    );

    /**
     * Count unread notifications
     */
    long countByTechnicianAndStatus(
            Technician technician,
            NotificationStatus status
    );

    /**
     * Latest five notifications (Dashboard)
     */
    List<Notification> findTop5ByTechnicianOrderByCreatedAtDesc(
            Technician technician
    );
}