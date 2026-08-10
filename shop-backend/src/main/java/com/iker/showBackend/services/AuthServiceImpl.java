package com.iker.showBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.iker.showBackend.dto.AuthResponse;
import com.iker.showBackend.dto.LoginRequest;
import com.iker.showBackend.dto.RegisterRequest;
import com.iker.showBackend.entities.User;
import com.iker.showBackend.repositories.AuthRepository;
import com.iker.showBackend.security.JwtService;

@Service
public class AuthServiceImpl implements IAuthService{

    @Autowired
    private AuthRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public AuthServiceImpl(AuthRepository repository, JwtService jwtService, PasswordEncoder encoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public void registerUser(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("El usuario ya existe!");
        }
        String hashedPassword = encoder.encode(request.getPassword());

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPhone(request.getPhone());
        user.setPassword(hashedPassword);

        repository.save(user);
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado!"));
    
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new RuntimeException("Las contraseñas no coinciden!");
        }

        String token = jwtService.generateToken(user.getUsername());

        return new AuthResponse(token);
    }
}
