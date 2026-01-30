package com.aacid0.fugitiva.webapi.modules.identity.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateUserResponse> createUser(@RequestBody CreateUserRequest request) {
        return ResponseEntity.ok(userService.createUser(request));
    }
}
