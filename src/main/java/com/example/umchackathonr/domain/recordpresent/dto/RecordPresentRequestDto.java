package com.example.umchackathonr.domain.recordpresent.dto;

import lombok.Getter;

public class RecordPresentRequestDto {

    @Getter
    public static class RecordPresentAddReq {
        private String title;
        private String description;
        private int price;
        private String category;
        private String name;
        private String picture;
        private Long userId;
    }
}
