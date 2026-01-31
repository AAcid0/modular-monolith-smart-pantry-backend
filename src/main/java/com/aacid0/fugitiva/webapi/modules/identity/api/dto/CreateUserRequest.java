package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateUserRequest(
        @NotBlank(message = "El email es requerido") @Email(message = "El email debe ser válido") String email,

        @NotBlank(message = "La contraseña es requerida") @Size(min = 6, max = 100, message = "La contraseña debe tener al menos 6 caracteres") String password_hash) {
}
