package com.example.umchackathonr.domain.friend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class FriendResponse {

    @Schema(description = "친구 ID", example = "1")
    private Long friendId;

    @Schema(description = "친구 이름", example = "훈이")
    private String friendName;
}
