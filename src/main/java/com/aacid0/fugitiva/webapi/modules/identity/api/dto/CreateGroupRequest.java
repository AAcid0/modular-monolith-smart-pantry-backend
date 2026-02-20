package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Builder;

@Builder
public record CreateGroupRequest(String name, UUID user_id, BigDecimal budget) {

}
