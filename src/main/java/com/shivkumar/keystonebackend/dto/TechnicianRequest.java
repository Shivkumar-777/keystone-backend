package com.shivkumar.keystonebackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TechnicianRequest {

    private String fullName;

    private String email;

    private String phone;

    private String specialization;

    private Integer experience;

    private Long companyId;
}