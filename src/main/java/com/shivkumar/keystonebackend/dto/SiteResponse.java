package com.shivkumar.keystonebackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteResponse {

    private Long id;

    private String siteName;

    private String address;

    private String city;

    private String state;

    private String postalCode;

    private Long customerId;

    private String customerName;
}