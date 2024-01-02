package com.example.umchackathonr.domain.friend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateFriendResponse {

    @Schema(description = "친구 ID", example = "1")
    private Long friendId;
}
