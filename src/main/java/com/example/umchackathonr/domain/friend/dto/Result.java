package com.example.umchackathonr.domain.friend.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Result<T> {

    @Schema(description = "전체 수", example = "1")
    private int count;

    @Schema(description = "조회 목록")
    private List<T> data;
}
