package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.UUID;

public record CreateFamilyGroupRequest(String name, UUID user_id) {

}
