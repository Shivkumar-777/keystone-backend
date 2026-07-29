package com.shivkumar.keystonebackend.entity;

import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "technicians")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Technician {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String specialization;

    private Integer experience;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TechnicianStatus status;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();

        if (status == null) {
            status = TechnicianStatus.AVAILABLE;
        }
    }
}