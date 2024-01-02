package com.example.umchackathonr.domain.friend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class CreateFriendRequest {

    @Schema(description = "이름", example = "훈이")
    @NotNull
    private String name;

    @Schema(description = "생일", example = "2010-10-04")
    private LocalDate birthday;
}
