package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import java.util.UUID;

public record JoinGroupRequest(
                UUID user_id,
                String invitationCode) {

}
