package com.shivkumar.keystonebackend.dto;

import lombok.Data;

@Data
public class CustomerRequest {

    private String customerName;
    private String email;
    private String phone;
    private String address;

    // Company to which this customer belongs
    private Long companyId;
}