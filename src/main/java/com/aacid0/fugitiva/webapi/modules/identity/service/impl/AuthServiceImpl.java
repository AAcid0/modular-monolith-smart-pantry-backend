package com.aacid0.fugitiva.webapi.modules.identity.service.impl;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.aacid0.fugitiva.webapi.common.exception.EmailUnavailableException;
import com.aacid0.fugitiva.webapi.common.exception.UnauthorizedTokenException;
import com.aacid0.fugitiva.webapi.common.exception.UserIdentificatorNotFoundException;
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
    private final AuthenticationManager authenticationManager;

    public AuthServiceImpl(IUserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService,
            AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
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

    @Override
    public CreateUserResponse loginUser(CreateUserRequest request) {
        try {
            authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(request.email(), request.password_hash()));
        } catch (Exception e) {
            throw new UserIdentificatorNotFoundException("Usuario no encontrado");
        }

        var user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));
        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);

        return new CreateUserResponse(user.getEmail(), jwtToken, refreshToken);
    }

    @Override
    public CreateUserResponse refreshToken(String authHeader) {
        final String refreshToken = authHeader.startsWith("Bearer ") ? authHeader.substring(7) : authHeader;

        if (refreshToken == null) {
            throw new UnauthorizedTokenException("Token no encontrado");
        }

        final String username = jwtService.extractUsername(refreshToken);
        final User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new UserIdentificatorNotFoundException("Usuario no encontrado"));

        if (!jwtService.validateToken(refreshToken, user)) {
            throw new UnauthorizedTokenException("Token inválido");
        }

        final String jwtToken = jwtService.generateToken(user);

        return new CreateUserResponse(user.getEmail(), jwtToken, refreshToken);
    }

}
