package com.example.umchackathonr.domain.recordpresent.dto;

import lombok.*;

import java.util.List;

public class RecordPresentResponseDto {

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RecordPresentDto {
        private Long id;
        private String title;
        private String picture;
        private String description;
        private String category;
        private Integer price;
        private String name;
    }

    @Getter
    @NoArgsConstructor
    @Builder
    @AllArgsConstructor
    public static class RecordPresentListDto {
        private List<RecordPresentDto> recordPresentDto;
    }
}
