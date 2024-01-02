package com.example.umchackathonr.domain.recordpresent.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

public class RecordPresentRequestDto {

    @Getter
    public static class RecordPresentAddReq {
        @Schema(description = "제목", example = "훈이의 선물")
        @NotNull
        private String title;
        @Schema(description = "설명", example = "그 친구에게 어디에서 선물 받음")
        @NotNull
        private String description;
        @Schema(description = "가격", example = "10000")
        @NotNull
        private int price;
        @Schema(description = "종류", example = "훈이")
        @NotNull
        private String category;
        @Schema(description = "이름", example = "맹구")
        @NotNull
        private String name;
        @Schema(description = "s3 주소", example = "http://amazons3....")
        @NotNull
        private String picture;
        @Schema(description = "글쓴이의 아이디", example = "1")
        @NotNull
        private Long userId;
    }

    @Getter
    public static class RecordPresentUpdateReq {
        @Schema(description = "제목", example = "훈이의 선물")
        @NotNull
        private String title;
        @Schema(description = "설명", example = "그 친구에게 어디에서 선물 받음")
        @NotNull
        private String description;
        @Schema(description = "가격", example = "10000")
        @NotNull
        private int price;
        @Schema(description = "종류", example = "훈이")
        @NotNull
        private String category;
        @Schema(description = "이름", example = "맹구")
        @NotNull
        private String name;
        @Schema(description = "s3 주소", example = "http://amazons3....")
        @NotNull
        private String picture;
    }
}
