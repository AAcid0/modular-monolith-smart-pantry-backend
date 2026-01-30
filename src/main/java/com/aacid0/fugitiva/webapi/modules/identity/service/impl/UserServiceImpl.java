package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aacid0.fugitiva.webapi.common.exception.EmailUnavailableException;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IUserRepository;
import com.aacid0.fugitiva.webapi.modules.identity.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
            throw new EmailUnavailableException("El email ingresado ya se encuentra en uso.");
        }

        User newUser = User.builder()
                .name(request.name())
                .email(request.email())
                .passwordHash(request.password_hash())
                .build();

        userRepository.save(newUser);

        return new CreateUserResponse(newUser.getName(), newUser.getEmail());
    }
}
