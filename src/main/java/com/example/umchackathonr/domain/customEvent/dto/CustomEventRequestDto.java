package com.example.umchackathonr.domain.customEvent.dto;

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
}
