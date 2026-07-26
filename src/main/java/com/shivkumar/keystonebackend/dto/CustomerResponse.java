package com.shivkumar.keystonebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CustomerResponse {

    private Long id;
    private String customerName;
    private String email;
    private String phone;
    private String address;

    private Long companyId;
    private String companyName;
}