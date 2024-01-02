package com.example.umchackathonr.domain.customEvent.convertor;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import com.example.umchackathonr.domain.user.User;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomEventConverter {


    public static CustomEvent toEntity(CustomEventRequestDto.creatCustomEventDto requestDto , Friend friend, User user) {
        return CustomEvent.builder()
                .title(requestDto.getTitle())
                .date(requestDto.getDate())
                .memo(requestDto.getMemo())
                .friend(friend)
                .user(user)
                .build();
    }

    public static CustomEventResponseDto.readCustomDto toReadCustomDto(CustomEvent customEvent, List<RecordPresentResponseDto.RecordPresentDto> recordPresents) {
        return CustomEventResponseDto.readCustomDto.builder()
                .recordPresents(recordPresents)
                .title(customEvent.getTitle())
                .date(customEvent.getDate())
                .memo(customEvent.getMemo())
                .build();
    }
}
