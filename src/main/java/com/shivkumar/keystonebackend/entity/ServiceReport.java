package com.shivkumar.keystonebackend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "service_reports")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ServiceReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 1000)
    private String workPerformed;

    @Column(length = 1000)
    private String technicianNotes;

    @Column(length = 500)
    private String customerFeedback;

    private Double hoursWorked;

    @Enumerated(EnumType.STRING)
    private ServiceStatus status;

    private LocalDateTime reportDate;

    @ManyToOne
    @JoinColumn(name = "work_order_id")
    private WorkOrder workOrder;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @PrePersist
    public void onCreate() {
        reportDate = LocalDateTime.now();
    }
}