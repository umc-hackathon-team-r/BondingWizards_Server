package com.example.umchackathonr.domain.customEvent.service;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.convertor.CustomEventConverter;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
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

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomEventService {

    private final CustomEventRepository customEventRepository;
    private final CustomEventConverter customEventConverter;

    private final FriendRepository friendRepository;
    private final FriendService friendService;

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

}
