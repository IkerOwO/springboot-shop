package com.iker.shopBackend.dto.Auth;

public record LoginResponse(
    String token,
    Long id,
    String username,
    String email,
    String role
) {}
