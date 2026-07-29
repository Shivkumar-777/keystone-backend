package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.NotificationResponse;
import com.shivkumar.keystonebackend.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Get all notifications of a technician
     */
    @GetMapping("/technician/{technicianId}")
    public ResponseEntity<List<NotificationResponse>> getNotifications(
            @PathVariable Long technicianId
    ) {
        return ResponseEntity.ok(
                notificationService.getTechnicianNotifications(technicianId)
        );
    }

    /**
     * Get unread notifications
     */
    @GetMapping("/technician/{technicianId}/unread")
    public ResponseEntity<List<NotificationResponse>> getUnreadNotifications(
            @PathVariable Long technicianId
    ) {
        return ResponseEntity.ok(
                notificationService.getUnreadNotifications(technicianId)
        );
    }

    /**
     * Count unread notifications
     */
    @GetMapping("/technician/{technicianId}/count")
    public ResponseEntity<Long> getUnreadCount(
            @PathVariable Long technicianId
    ) {
        return ResponseEntity.ok(
                notificationService.getUnreadCount(technicianId)
        );
    }

    /**
     * Latest notifications (Dashboard)
     */
    @GetMapping("/technician/{technicianId}/recent")
    public ResponseEntity<List<NotificationResponse>> getRecentNotifications(
            @PathVariable Long technicianId
    ) {
        return ResponseEntity.ok(
                notificationService.getRecentNotifications(technicianId)
        );
    }

    /**
     * Mark notification as read
     */
    @PutMapping("/{notificationId}/read")
    public ResponseEntity<String> markAsRead(
            @PathVariable Long notificationId
    ) {

        notificationService.markAsRead(notificationId);

        return ResponseEntity.ok("Notification marked as read.");
    }

}