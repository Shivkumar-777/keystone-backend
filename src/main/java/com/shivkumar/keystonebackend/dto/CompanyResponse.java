package com.shivkumar.keystonebackend.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompanyResponse {

    private Long id;
    private String companyName;
    private String email;
    private String phone;
    private String address;
    private String website;
}