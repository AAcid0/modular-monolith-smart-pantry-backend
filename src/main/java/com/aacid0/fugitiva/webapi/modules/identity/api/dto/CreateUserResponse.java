package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

public record CreateUserResponse(
                String email,
                String token,
                String refreshToken) {

}
