package com.shivkumar.keystonebackend.entity;

import com.shivkumar.keystonebackend.enums.SLAStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "work_orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WorkOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    private WorkOrderPriority priority;

    @Enumerated(EnumType.STRING)
    private WorkOrderStatus status;

    private LocalDate scheduledDate;

    private LocalDate completedDate;

    /**
     * SLA deadline for this work order.
     */
    private LocalDateTime slaDueDate;

    /**
     * Current SLA status.
     */
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SLAStatus slaStatus = SLAStatus.ON_TIME;

    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "site_id")
    private Site site;

    @ManyToOne
    @JoinColumn(name = "technician_id")
    private Technician technician;

    @PrePersist
    public void onCreate() {
        createdAt = LocalDateTime.now();

        if (slaStatus == null) {
            slaStatus = SLAStatus.ON_TIME;
        }
    }
}