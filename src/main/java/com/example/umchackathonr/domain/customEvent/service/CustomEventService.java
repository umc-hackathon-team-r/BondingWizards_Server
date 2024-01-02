package com.example.umchackathonr.domain.customEvent.service;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.convertor.CustomEventConverter;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.repository.CustomEventRepository;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.friend.FriendRepository;
import com.example.umchackathonr.domain.friend.FriendService;
import com.example.umchackathonr.domain.friend.dto.FriendRequest;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomEventService {

    private final CustomEventRepository customEventRepository;
    private final CustomEventConverter customEventConverter;

    private final FriendRepository friendRepository;
    private final FriendService friendService;

    public void creatCustomEvent(CustomEventRequestDto.creatCustomEventDto customEventRequestDto ,Long userId){
        Friend friendByNameAndBirthday = friendRepository.findFriendByNameAndBirthday(customEventRequestDto.getTarget(), customEventRequestDto.getDate());
        if(friendByNameAndBirthday.equals(null)){
          // 여기에 친구가 없으면 친구를 생성하는 로직을 만들어주세요.
        }
        CustomEvent customEvent = customEventConverter.toEntity(customEventRequestDto,friendByNameAndBirthday);
        customEventRepository.save(customEvent);
    }

}
