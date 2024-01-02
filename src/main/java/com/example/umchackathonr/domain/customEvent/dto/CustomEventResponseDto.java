package com.example.umchackathonr.domain.customEvent.dto;

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
        private List<EventResponseDto> data;

        public static ListEventResponseDto from(List<CustomEvent> customEvents) {
            List<EventResponseDto> eventResponseDtos = customEvents.stream()
                    .map(EventResponseDto::from)
                    .collect(Collectors.toList());

            return new ListEventResponseDto(eventResponseDtos);
        }
    }

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    public static class EventResponseDto {
        private String title;

        public static EventResponseDto from(CustomEvent customEvent) {
            return new EventResponseDto(customEvent.getTitle());
        }
    }

}