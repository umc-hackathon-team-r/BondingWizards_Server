package com.example.umchackathonr.domain.customEvent.dto;

import com.example.umchackathonr.domain.Event.Event;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

public class CustomEventResponseDto {

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

}