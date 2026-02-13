package com.aacid0.fugitiva.webapi.modules.identity.service;

import java.util.UUID;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;

public interface IUserService {
    UpdateUserResponse updateUser(UUID user_id, UpdateUserRequest user);

    User getUserById(UUID user_id);
}
