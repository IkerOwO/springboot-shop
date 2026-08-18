package com.iker.shopBackend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iker.shopBackend.dto.Auth.LoginRequest;
import com.iker.shopBackend.dto.Auth.LoginResponse;
import com.iker.shopBackend.dto.Auth.RegisterRequest;
import com.iker.shopBackend.services.AuthService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    public AuthController(AuthService service) {
        this.service = service;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@Valid @RequestBody RegisterRequest request) {
        service.createUser(request);
        return ResponseEntity.ok("User created!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(service.loginUser(request));
    }
}
