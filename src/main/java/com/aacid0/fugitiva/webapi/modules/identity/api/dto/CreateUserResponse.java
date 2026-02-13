package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.UUID;

public record CreateUserResponse(
        UUID user_id,
        String token,
        String refreshToken) {

}
