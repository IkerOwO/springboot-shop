package com.iker.shopBackend.dto.Auth;

import com.iker.shopBackend.validation.IsRequired;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @Email
    @NotBlank
    @IsRequired
    private String email;

    @NotBlank
    @IsRequired
    private String password;
}
