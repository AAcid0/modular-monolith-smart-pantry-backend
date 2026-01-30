package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.List;
import java.util.UUID;

public record GetAllFamilyGroupsByUserIdResponseBody(
                UUID id,
                String name,
                String invitationCode,
                List<UserSummaryResponse> members) {
}
