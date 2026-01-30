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

import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupRequest;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.CreateFamilyGroupResponse;
import com.aacid0.fugitiva.webapi.modules.identity.api.dto.GetFamilyGroupsByUserIdResponse;
import com.aacid0.fugitiva.webapi.modules.identity.service.IFamilyGroupService;

@RestController
@RequestMapping("/api/v1/family-groups")
public class FamilyGroupController {
    public final IFamilyGroupService familyGroupService;

    public FamilyGroupController(IFamilyGroupService familyGroupService) {
        this.familyGroupService = familyGroupService;
    }

    @PostMapping("/create")
    public ResponseEntity<CreateFamilyGroupResponse> createFamilyGroup(@RequestBody CreateFamilyGroupRequest request) {
        return ResponseEntity.ok(familyGroupService.createFamilyGroup(request));
    }

    @GetMapping("/user/{user_id}/get")
    public ResponseEntity<GetFamilyGroupsByUserIdResponse> getAllFamilyGroupsByUserId(@PathVariable UUID user_id) {
        return ResponseEntity.ok(familyGroupService.getAllFamilyGroupsByUserId(user_id));
    }
}
