package com.aacid0.fugitiva.webapi.modules.identity.api.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateUserRequest(@JsonProperty("name") String name, @JsonProperty("image_url") String imageUrl) {

}
