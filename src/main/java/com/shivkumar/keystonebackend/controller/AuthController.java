package com.shivkumar.keystonebackend.controller;

import com.shivkumar.keystonebackend.dto.LoginRequest;
import com.shivkumar.keystonebackend.dto.LoginResponse;
import com.shivkumar.keystonebackend.dto.RegisterRequest;
import com.shivkumar.keystonebackend.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public String register(@Valid @RequestBody RegisterRequest request) {
        return authService.register(request);
    }

    @PostMapping("/login")
    public LoginResponse login(@Valid @RequestBody LoginRequest request) {
        return authService.login(request);
    }
}