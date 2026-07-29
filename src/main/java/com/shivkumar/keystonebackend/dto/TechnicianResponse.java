package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.enums.TechnicianStatus;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicianResponse {

    private Long id;

    private String fullName;

    private String email;

    private String phone;

    private String specialization;

    private Integer experience;

    private TechnicianStatus status;

    private Long companyId;

    private String companyName;
}