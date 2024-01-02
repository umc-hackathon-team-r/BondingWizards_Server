package com.example.umchackathonr.domain.friend;


import com.example.umchackathonr.domain.friend.dto.CreateFriendRequest;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@RequiredArgsConstructor
@Service
public class FriendService {

    private final FriendRepository friendRepository;

    //친구 생성
    public Long save(CreateFriendRequest request) {
        String name = request.getName();
        LocalDate birthday = request.getBirthday();

        if (friendRepository.existsByNameAndBirthday(name, birthday)) {
            throw new RestApiException(UserErrorCode.DUPLICATION_FRIEND);
        }

        Friend friend = Friend.builder()
                .name(name)
                .birthday(birthday)
                .build();

        Friend savedFriend = friendRepository.save(friend);

        return savedFriend.getId();
    }
}
