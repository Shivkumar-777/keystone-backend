package com.shivkumar.keystonebackend.dto;

import lombok.Data;

@Data
public class UpdateProfileRequest {

    private String fullName;
    private String phone;
}