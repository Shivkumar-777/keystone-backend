package com.shivkumar.keystonebackend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteRequest {

    private String siteName;

    private String address;

    private String city;

    private String state;

    private String postalCode;

    private Long customerId;
}