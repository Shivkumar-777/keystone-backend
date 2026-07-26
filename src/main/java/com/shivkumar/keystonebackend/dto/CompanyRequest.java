package com.shivkumar.keystonebackend.dto;

import lombok.Data;

@Data
public class CompanyRequest {

    private String companyName;
    private String email;
    private String phone;
    private String address;
    private String website;
}