package com.example.umchackathonr.domain.customEvent.dto;

import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

}
