package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.UUID;

import lombok.Builder;

@Builder
public record CreateGroupRequest(String name, UUID user_id) {

}
