package com.example.umchackathonr.domain.customEvent.convertor;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.friend.Friend;
import org.springframework.stereotype.Component;

@Component
public class CustomEventConverter {

//    public static CustomEventResponseDto.CustomEventDto toCustomEventDto(CustomEvent customEvent) {
//        return CustomEventResponseDto.CustomEventDto.builder()
//                .title(customEvent.getTitle())
//                .date(customEvent.getDate())
//                .memo(customEvent.getMemo())
//                .target(customEvent.getTarget())
//                .build();
//    }
//
//    public static CustomEventResponseDto.CustomEventListDto toCustomEventListDto(List<CustomEvent> customEvents) {
//        List<CustomEventResponseDto.CustomEventDto> customEventDtos = new ArrayList<>();
//
//        for (CustomEvent c : customEvents) {
//            customEventDtos.add(toCustomEventDto(c));
//        }
//
//        return CustomEventResponseDto.CustomEventListDto.builder().customEventDto(customEventDtos).build();
//    }

    public static CustomEvent toEntity(CustomEventRequestDto.creatCustomEventDto requestDto , Friend friend) {
        return CustomEvent.builder()
                .title(requestDto.getTitle())
                .date(requestDto.getDate())
                .memo(requestDto.getMemo())
                .friend(friend)
                .build();
    }
}
