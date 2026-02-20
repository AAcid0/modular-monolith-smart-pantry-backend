package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.List;
import java.util.UUID;

public record GetAllGroupsByUserIdResponseBody(
                UUID id,
                String name,
                String invitation_code,
                List<UserSummaryResponse> members) {
}
