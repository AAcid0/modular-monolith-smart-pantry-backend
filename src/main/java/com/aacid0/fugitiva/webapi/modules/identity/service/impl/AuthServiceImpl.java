package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aacid0.fugitiva.webapi.common.exception.EmailUnavailableException;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.repository.IUserRepository;
import com.aacid0.fugitiva.webapi.modules.identity.security.JwtService;
import com.aacid0.fugitiva.webapi.modules.identity.service.IAuthService;

import jakarta.transaction.Transactional;

@Service
public class AuthServiceImpl implements IAuthService {

    private final IUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public CreateUserResponse registerUser(CreateUserRequest request) {
        Optional<User> user = userRepository.findByEmail(request.email());
        if (user.isPresent()) {
            throw new EmailUnavailableException("El email ingresado ya se encuentra en uso.");
        }

        User newUser = User.builder()
                .email(request.email())
                .passwordHash(passwordEncoder.encode(request.password_hash()))
                .build();

        userRepository.save(newUser);

        var jwtToken = jwtService.generateToken(newUser);
        var refreshToken = jwtService.generateRefreshToken(newUser);

        return new CreateUserResponse(newUser.getEmail(), jwtToken, refreshToken);
    }

}
