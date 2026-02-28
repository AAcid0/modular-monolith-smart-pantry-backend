package com.aacid0.fugitiva.webapi.modules.identity.service;

import java.util.UUID;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetGroupsByUserIdResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.JoinGroupResponse;

public interface IGroupService {
    CreateGroupResponse createGroup(CreateGroupRequest request, boolean isPublic);

    GetGroupsByUserIdResponse getAllGroupsByUserId(UUID userId);

    JoinGroupResponse joinGroup(UUID userId, String invitationCode);
}
