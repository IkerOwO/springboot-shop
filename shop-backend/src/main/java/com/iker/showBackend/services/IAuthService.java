package com.iker.showBackend.services;

import com.iker.showBackend.dto.AuthResponse;
import com.iker.showBackend.dto.LoginRequest;
import com.iker.showBackend.dto.RegisterRequest;

public interface IAuthService {
    void registerUser(RegisterRequest request);

    AuthResponse login(LoginRequest request); 
}
