package com.example.umchackathonr.domain.customEvent.service;

import com.example.umchackathonr.domain.Event.Event;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomEventService {

    private final CustomEventRepository customEventRepository;
    private final CustomEventConverter customEventConverter;
    private final FriendService friendService;

    private final FriendRepository friendRepository;

    private final UserRepository userRepository;

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

    public CustomEventResponseDto.ListEventResponseDto getListCustomEvent(LocalDate date){
        List<CustomEvent> customEventByDate = customEventRepository.findCustomEventByDate(date);
        List<CustomEventResponseDto.EventResponseDto> eventResponseDtos = customEventByDate.stream()
                .map(CustomEventResponseDto.EventResponseDto::from)
                .collect(Collectors.toList());

        return new CustomEventResponseDto.ListEventResponseDto(eventResponseDtos);
    }

    // 이벤트 수정
    public void update(CustomEventRequestDto.updateCustomEventDto request, Long eventId) {
        CustomEvent customEvent = customEventRepository.findById(eventId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.INACTIVE_EVENT));

        customEvent.updateEventInformation(request);

        customEventRepository.save(customEvent);
    }

    // 이벤트 삭제
    public void delete(Long eventId) {
        customEventRepository.findById(eventId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.INACTIVE_EVENT));

        customEventRepository.deleteById(eventId);
    }
}