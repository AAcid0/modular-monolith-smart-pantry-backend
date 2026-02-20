package com.aacid0.fugitiva.webapi.modules.identity.service;

import java.util.UUID;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetGroupsByUserIdResponse;

public interface IGroupService {
    CreateGroupResponse createGroup(CreateGroupRequest request, boolean isPublic);

    GetGroupsByUserIdResponse getAllGroupsByUserId(UUID userId);

    void joinGroup(UUID userId, String invitationCode);
}
