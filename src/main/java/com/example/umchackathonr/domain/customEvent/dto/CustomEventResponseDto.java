package com.example.umchackathonr.domain.customEvent.dto;

import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

import com.example.umchackathonr.domain.Event.Event;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

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

}

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

