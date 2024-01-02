package com.example.umchackathonr.domain.customEvent.service;

import com.example.umchackathonr.domain.Event.Event;
import com.example.umchackathonr.domain.Event.EventRepository;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.convertor.CustomEventConverter;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.customEvent.repository.CustomEventRepository;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.friend.FriendRepository;
import com.example.umchackathonr.domain.friend.FriendService;
import com.example.umchackathonr.domain.friend.dto.FriendRequest;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomEventService {

    private final CustomEventRepository customEventRepository;
    private final CustomEventConverter customEventConverter;

    private final FriendRepository friendRepository;

    private final FriendService friendService;

    private final UserRepository userRepository;

    private final EventRepository eventRepository;

    public void creatCustomEvent(CustomEventRequestDto.creatCustomEventDto customEventRequestDto, Long userId) {
        Friend friendByNameAndBirthday = friendRepository.findFriendByNameAndBirthday(customEventRequestDto.getTarget(), customEventRequestDto.getDate());

        if (friendByNameAndBirthday == null) {
            FriendRequest request = FriendRequest.builder()
                    .name(customEventRequestDto.getTarget())
                    .birthday(customEventRequestDto.getDate())
                    .build();
            friendService.save(userId, request);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.INACTIVE_USER));
        CustomEvent customEvent = customEventConverter.toEntity(customEventRequestDto, friendByNameAndBirthday, user);
        customEventRepository.save(customEvent);
    }

    public CustomEventResponseDto.ListEventResponseDto getListCustomEvent(LocalDate date) {
        List<CustomEvent> customEvent = customEventRepository.findCustomEventByDate(date);
        Event event = eventRepository.findEventByDate(date);
        mergeEvent(customEvent,event);

        List<CustomEventResponseDto.CommonEventDto> mergedEventDtos = mergeEvent(customEvent, event);

        return new CustomEventResponseDto.ListEventResponseDto(mergedEventDtos);
    }

    // 사용자 입력 이벤트와 자동 생성 Event 병합 코드
    public List<CustomEventResponseDto.CommonEventDto> mergeEvent(List<CustomEvent> customEvents, Event event) {
        List<CustomEventResponseDto.CommonEventDto> commonEventDtos = new ArrayList<>();
        commonEventDtos.add(CustomEventResponseDto.CommonEventDto.fromEvent(event));

        List<CustomEventResponseDto.CommonEventDto> customEventDtos = customEvents.stream()
                .map(CustomEventResponseDto.CommonEventDto::fromCustomEvent)
                .collect(Collectors.toList());

        commonEventDtos.addAll(customEventDtos);

        return commonEventDtos;
    }




}
