package com.example.umchackathonr.domain.customEvent.dto;

import com.example.umchackathonr.domain.Event.Event;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import java.time.LocalDate;
import java.util.List;


public class CustomEventResponseDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class readCustomDto{

        private String title;
        private LocalDate date;
        private String memo;
        private List<RecordPresentResponseDto.RecordPresentDto> recordPresents;
    }

    // 목록 조회
    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class ListEventResponseDto {
        private List<CommonEventDto> data;

    }


    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class CommonEventDto{

        private String title;

        public static CommonEventDto fromCustomEvent(CustomEvent customEvent) {
            return new CommonEventDto(customEvent.getTitle());
        }

        public static CommonEventDto fromEvent(Event event) {
            return new CommonEventDto(event.getTitle());
        }

    }

    @Getter
    @AllArgsConstructor
    public static class CreateCustomEventResponse {
        @Schema(description = "이벤트 ID", example = "1")
        private Long eventId;
    }



}

