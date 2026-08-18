package com.iker.shopBackend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.iker.shopBackend.dto.Auth.LoginRequest;
import com.iker.shopBackend.dto.Auth.LoginResponse;
import com.iker.shopBackend.dto.Auth.RegisterRequest;
import com.iker.shopBackend.entities.User;
import com.iker.shopBackend.exceptions.PasswordsDontMatchException;
import com.iker.shopBackend.exceptions.UserAlreadyExistsException;
import com.iker.shopBackend.exceptions.UserDontExistsException;
import com.iker.shopBackend.repositories.AuthRepository;
import com.iker.shopBackend.security.JwtService;
import com.iker.shopBackend.security.SecurityUser;

@Service
public class AuthService implements UserDetailsService{

    @Autowired
    private AuthRepository repository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PasswordEncoder encoder;

    public AuthService(AuthRepository repository, JwtService jwtService, PasswordEncoder encoder) {
        this.repository = repository;
        this.jwtService = jwtService;
        this.encoder = encoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return repository.findByUsername(username)
            .map(SecurityUser::new)
            .orElseThrow(() -> new UsernameNotFoundException("User not found!"));
    }

    public void createUser(RegisterRequest request) {
        if (repository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException("Email associated with another account");
        }
        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(encoder.encode(request.getPassword()));
        user.setRole(request.getRole());
        repository.save(user);
    }

    public LoginResponse loginUser(LoginRequest request) {
        User user = repository.findByEmail(request.getEmail())
            .orElseThrow(() -> new UserDontExistsException("The user doesn't exists!"));
        
        if (!encoder.matches(request.getPassword(), user.getPassword())) {
            throw new PasswordsDontMatchException("Login incorrect!");
        }

        return new LoginResponse(
            jwtService.generateToken(user),
            user.getId(),
            user.getUsername(),
            user.getEmail(),
            user.getRole()
        );
    }
}
