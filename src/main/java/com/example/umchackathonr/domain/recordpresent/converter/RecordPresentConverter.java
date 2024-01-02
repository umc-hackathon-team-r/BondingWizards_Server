package com.example.umchackathonr.domain.recordpresent.converter;

import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentRequestDto;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;

import java.util.ArrayList;
import java.util.List;

public class RecordPresentConverter {

    public static RecordPresentResponseDto.RecordPresentDto toRecordPresentDto(RecordPresent recordPresent) {
        return RecordPresentResponseDto.RecordPresentDto.builder()
                                        .id(recordPresent.getId())
                                        .price(recordPresent.getPrice())
                                        .title(recordPresent.getTitle())
                                        .picture(recordPresent.getPicture())
                                        .name(recordPresent.getName())
                                        .category(recordPresent.getCategory())
                                        .description(recordPresent.getDescription())
                                        .build();
    }

    public static RecordPresentResponseDto.RecordPresentListDto toRecordPresentListDto(List<RecordPresent> recordPresents) {
        List<RecordPresentResponseDto.RecordPresentDto> recordPresentDtos = new ArrayList<>();

        for(RecordPresent r : recordPresents) {
            recordPresentDtos.add(toRecordPresentDto(r));
        }

        return RecordPresentResponseDto.RecordPresentListDto.builder().recordPresentDto(recordPresentDtos).build();
    }

    public static RecordPresent toRecordPresent(RecordPresentRequestDto.RecordPresentAddReq recordReq) {


        return RecordPresent.builder()
                .name(recordReq.getName())
                .picture(recordReq.getPicture())
                .title(recordReq.getTitle())
                .category(recordReq.getCategory())
                .description(recordReq.getDescription())
                .price(recordReq.getPrice()).build();

    }
}
