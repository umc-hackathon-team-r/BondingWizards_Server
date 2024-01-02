package com.example.umchackathonr.domain.customEvent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


public class CustomEventRequestDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class creatCustomEventDto{
        private String title;
        private LocalDate date;
        private String memo;
        private String target;

    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class memoDto {
        @Schema(description = "메모", example = "메모 수정 및 작성")
        private String memo;
    }
}
