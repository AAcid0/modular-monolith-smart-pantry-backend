package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

public record CreateUserRequest(
                String name,
                String email,
                String password_hash) {
}
