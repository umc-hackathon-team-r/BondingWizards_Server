package com.example.umchackathonr.domain.customEvent.service;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.convertor.CustomEventConverter;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.customEvent.repository.CustomEventRepository;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.friend.FriendRepository;
import com.example.umchackathonr.domain.friend.FriendService;
import com.example.umchackathonr.domain.friend.dto.FriendRequest;
import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.RecordPresentRepository;
import com.example.umchackathonr.domain.recordpresent.RecordPresentService;
import com.example.umchackathonr.domain.recordpresent.converter.RecordPresentConverter;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomEventService {

    private final CustomEventRepository customEventRepository;
    private final CustomEventConverter customEventConverter;

    private final FriendRepository friendRepository;
    private final FriendService friendService;

    private final UserRepository userRepository;
    private final RecordPresentRepository recordPresentRepository;
    private final RecordPresentService recordPresentService;

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

    @Transactional(readOnly = true)
    public CustomEventResponseDto.readCustomDto readCustomEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId).get();

        CustomEvent customEvent = customEventRepository.findById(eventId).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_CUSTOM_EVENT);
        });

        List<RecordPresent> recordPresents = recordPresentRepository.findAllByUser(user);
        List<RecordPresentResponseDto.RecordPresentDto> recordPresentDtos = new ArrayList<>();

        for(RecordPresent r : recordPresents) {
            recordPresentDtos.add(RecordPresentConverter.toRecordPresentDto(r));
        }

        CustomEventResponseDto.readCustomDto readCustomDto = CustomEventConverter.toReadCustomDto(customEvent, recordPresentDtos);
        
        return readCustomDto;
    }

}
