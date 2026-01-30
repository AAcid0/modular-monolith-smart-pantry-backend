package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.List;

public record GetFamilyGroupsByUserIdResponse(
        List<GetAllFamilyGroupsByUserIdResponseBody> familyGroups) {

}
