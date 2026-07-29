package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.enums.NotificationStatus;
import com.shivkumar.keystonebackend.enums.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationResponse {

    private Long id;

    private NotificationType type;

    private String title;

    private String message;

    private NotificationStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime readAt;
}