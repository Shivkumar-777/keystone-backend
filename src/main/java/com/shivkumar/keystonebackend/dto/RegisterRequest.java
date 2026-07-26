package com.shivkumar.keystonebackend.dto;

import com.shivkumar.keystonebackend.enums.Role;
import lombok.Data;

@Data
public class RegisterRequest {

    private String fullName;
    private String email;
    private String password;
    private String phone;
    private Role role;

}