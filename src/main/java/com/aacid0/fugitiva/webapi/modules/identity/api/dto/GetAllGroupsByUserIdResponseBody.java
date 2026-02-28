package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

public record GetAllGroupsByUserIdResponseBody(
                UUID id,
                String name,
                String invitation_code,
                BigDecimal budget,
                Boolean is_public,
                List<UserSummaryResponse> members) {
}
