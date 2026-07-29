package com.shivkumar.keystonebackend.service;

import com.shivkumar.keystonebackend.dto.NotificationResponse;
import com.shivkumar.keystonebackend.entity.Notification;
import com.shivkumar.keystonebackend.entity.Technician;
import com.shivkumar.keystonebackend.enums.NotificationStatus;
import com.shivkumar.keystonebackend.enums.NotificationType;
import com.shivkumar.keystonebackend.repository.NotificationRepository;
import com.shivkumar.keystonebackend.repository.TechnicianRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    private final TechnicianRepository technicianRepository;

    /**
     * Create Notification
     */
    public Notification createNotification(
            Technician technician,
            NotificationType type,
            String title,
            String message
    ) {

        Notification notification = Notification.builder()
                .technician(technician)
                .type(type)
                .title(title)
                .message(message)
                .status(NotificationStatus.UNREAD)
                .createdAt(LocalDateTime.now())
                .build();

        return notificationRepository.save(notification);
    }

    /**
     * Get All Notifications
     */
    public List<NotificationResponse> getTechnicianNotifications(Long technicianId) {

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found"));

        return notificationRepository.findByTechnicianOrderByCreatedAtDesc(technician)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Get Unread Notifications
     */
    public List<NotificationResponse> getUnreadNotifications(Long technicianId) {

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found"));

        return notificationRepository
                .findByTechnicianAndStatusOrderByCreatedAtDesc(
                        technician,
                        NotificationStatus.UNREAD
                )
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Mark Notification as Read
     */
    public void markAsRead(Long notificationId) {

        Notification notification = notificationRepository.findById(notificationId)
                .orElseThrow(() ->
                        new EntityNotFoundException("Notification not found"));

        notification.setStatus(NotificationStatus.READ);
        notification.setReadAt(LocalDateTime.now());

        notificationRepository.save(notification);
    }

    /**
     * Count Unread Notifications
     */
    public long getUnreadCount(Long technicianId) {

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found"));

        return notificationRepository.countByTechnicianAndStatus(
                technician,
                NotificationStatus.UNREAD
        );
    }

    /**
     * Latest Dashboard Notifications
     */
    public List<NotificationResponse> getRecentNotifications(Long technicianId) {

        Technician technician = technicianRepository.findById(technicianId)
                .orElseThrow(() -> new EntityNotFoundException("Technician not found"));

        return notificationRepository.findTop5ByTechnicianOrderByCreatedAtDesc(technician)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    /**
     * Entity -> DTO
     */
    private NotificationResponse mapToResponse(Notification notification) {

        return NotificationResponse.builder()
                .id(notification.getId())
                .type(notification.getType())
                .title(notification.getTitle())
                .message(notification.getMessage())
                .status(notification.getStatus())
                .createdAt(notification.getCreatedAt())
                .readAt(notification.getReadAt())
                .build();
    }
}