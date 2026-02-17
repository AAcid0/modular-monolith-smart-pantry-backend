package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;
import com.aacid0.fugitiva.webapi.common.exception.UserIdentificatorNotFoundException;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IUserRepository;
import com.aacid0.fugitiva.webapi.modules.identity.service.IGroupService;
import com.aacid0.fugitiva.webapi.modules.identity.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;
    private final IGroupService groupService;

    public UserServiceImpl(IUserRepository userRepository, IGroupService groupService) {
        this.userRepository = userRepository;
        this.groupService = groupService;
    }

    @Override
    public UpdateUserResponse updateUser(UUID user_id, UpdateUserRequest incomingUser) {
        User updatedUser = userRepository.findById(user_id)
                .map(existingUser -> {
                    if (incomingUser.name() != null && !incomingUser.name().isEmpty()) {
                        existingUser.setName(incomingUser.name());
                    }

                    if (incomingUser.imageUrl() != null) {
                        existingUser.setImageUrl(incomingUser.imageUrl());
                    }

                    return userRepository.save(existingUser);
                })
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));

        String firstName = updatedUser.getName().trim().split(" ")[0];
        groupService.createGroup(CreateGroupRequest.builder()
                .user_id(updatedUser.getId())
                .name("Espacio de " + firstName)
                .build());

        return new UpdateUserResponse(updatedUser.getId().toString());
    }

    @Override
    public User getUserById(UUID user_id) {
        return userRepository.findById(user_id)
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));
    }
}
