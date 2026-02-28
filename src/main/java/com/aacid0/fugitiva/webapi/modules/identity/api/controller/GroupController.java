package com.aacid0.fugitiva.webapi.modules.identity.api.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetGroupsByUserIdResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.JoinGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.JoinGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.service.IGroupService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/groups")
@Slf4j
public class GroupController {
    public final IGroupService groupService;

    public GroupController(IGroupService groupService) {
        this.groupService = groupService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateGroupResponse> createGroup(@RequestBody CreateGroupRequest request) {
        log.info("DTO Create Group recibido: {}", request);
        return ResponseEntity.ok(groupService.createGroup(request, true));
    }

    @GetMapping("/user/{user_id}/get")
    public ResponseEntity<GetGroupsByUserIdResponse> getAllGroupsByUserId(@PathVariable UUID user_id) {
        log.info("DTO Get Groups by User ID recibido: {}", user_id);
        return ResponseEntity.ok(groupService.getAllGroupsByUserId(user_id));
    }

    @PostMapping("/join")
    public ResponseEntity<JoinGroupResponse> joinGroup(@RequestBody JoinGroupRequest request) {
        log.info("DTO Join Group recibido: {}", request);
        return ResponseEntity.ok(groupService.joinGroup(request.user_id(), request.invitation_code()));
    }
}
