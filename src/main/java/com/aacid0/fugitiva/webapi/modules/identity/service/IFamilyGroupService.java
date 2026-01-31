package com.aacid0.fugitiva.webapi.modules.identity.service;

import java.util.UUID;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetFamilyGroupsByUserIdResponse;

public interface IFamilyGroupService {
    CreateFamilyGroupResponse createFamilyGroup(CreateFamilyGroupRequest request);

    GetFamilyGroupsByUserIdResponse getAllFamilyGroupsByUserId(UUID user_id);

    void joinFamilyGroup(UUID userId, String invitationCode);
}
