package com.example.umchackathonr.domain.friend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FriendRequest {

    @Schema(description = "이름", example = "훈이")
    @NotNull
    private String name;

    @Schema(description = "생일", example = "2010-10-04")
    private LocalDate birthday;

}
