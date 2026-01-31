package com.aacid0.fugitiva.webapi.modules.identity.service;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserResponse;

public interface IAuthService {
    CreateUserResponse registerUser(CreateUserRequest request);

    CreateUserResponse loginUser(CreateUserRequest request);

    CreateUserResponse refreshToken(String refreshToken);
}
