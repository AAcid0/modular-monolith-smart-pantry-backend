package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.UUID;

public record UserSummaryResponse(
        UUID id,
        String name,
        String email) {
}
