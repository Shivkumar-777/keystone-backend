package com.shivkumar.keystonebackend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SiteRequest {

    @NotBlank(message = "Site name is required")
    @Size(max = 100, message = "Site name cannot exceed 100 characters")
    private String siteName;

    @NotBlank(message = "Address is required")
    @Size(max = 255, message = "Address cannot exceed 255 characters")
    private String address;

    @NotBlank(message = "City is required")
    @Size(max = 100, message = "City cannot exceed 100 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 100, message = "State cannot exceed 100 characters")
    private String state;

    @NotBlank(message = "Postal code is required")
    @Pattern(
            regexp = "^\\d{6}$",
            message = "Postal code must be a valid 6-digit PIN code"
    )
    private String postalCode;

    @NotNull(message = "Customer ID is required")
    private Long customerId;
}