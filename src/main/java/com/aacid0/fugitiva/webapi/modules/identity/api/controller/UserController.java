package com.aacid0.fugitiva.webapi.modules.identity.api.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.UpdateUserResponse;
import com.aacid0.fugitiva.webapi.modules.identity.domain.models.User;
import com.aacid0.fugitiva.webapi.modules.identity.service.IUserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{user_id}/update")
    public ResponseEntity<UpdateUserResponse> updateUser(@PathVariable UUID user_id,
            @RequestBody UpdateUserRequest user) {
        return ResponseEntity.ok(userService.updateUser(user_id, user));
    }

    @GetMapping("/{user_id}/get")
    public ResponseEntity<User> getUserById(@PathVariable UUID user_id) {
        return ResponseEntity.ok(userService.getUserById(user_id));
    }
}
