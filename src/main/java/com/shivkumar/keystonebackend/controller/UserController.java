package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.UpdateProfileRequest;
import com.shivkumar.keystonebackend.dto.UserResponse;
import com.shivkumar.keystonebackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    // Get Profile
    @GetMapping("/me")
    public UserResponse getCurrentUser(Authentication authentication) {
        return userService.getCurrentUser(authentication);
    }

    // Update Profile
    @PutMapping("/me")
    public UserResponse updateProfile(
            Authentication authentication,
            @RequestBody UpdateProfileRequest request) {

        return userService.updateProfile(authentication, request);
    }
}