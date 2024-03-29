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
import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.recordpresent.RecordPresentRepository;
import com.example.umchackathonr.domain.recordpresent.converter.RecordPresentConverter;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomEventService {

  private final CustomEventRepository customEventRepository;
  private final CustomEventConverter customEventConverter;
  private final FriendService friendService;

  private final FriendRepository friendRepository;

  private final UserRepository userRepository;
  private final EventRepository eventRepository;

  private final RecordPresentRepository recordPresentRepository;



    public Long creatCustomEvent(CustomEventRequestDto.creatCustomEventDto customEventRequestDto, Long userId) {
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
        CustomEvent saveCustomEvent = customEventRepository.save(customEvent);
        return saveCustomEvent.getId();
    }

    // 날짜별 이벤트 목록 조회
    public CustomEventResponseDto.ListEventResponseDto getListCustomEvent(LocalDate date, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RestApiException(UserErrorCode.INACTIVE_USER));
        List<CustomEvent> customEvent = customEventRepository.findCustomEventByDateAndUser(date,user);
        Event event = eventRepository.findEventByDate(date);

        // 이벤트가 없을 경우
        if (customEvent.isEmpty() && event == null) {
            List<CustomEventResponseDto.CommonEventDto> noEventDto = Collections.singletonList(
                    new CustomEventResponseDto.CommonEventDto("일정이 없습니다")
            );
            return new CustomEventResponseDto.ListEventResponseDto(noEventDto);
        }

        mergeEvent(customEvent,event);
        List<CustomEventResponseDto.CommonEventDto> mergedEventDtos = mergeEvent(customEvent, event);
        return new CustomEventResponseDto.ListEventResponseDto(mergedEventDtos);
    }

    // 사용자 입력 이벤트와 자동 생성 Event 병합 코드
    public List<CustomEventResponseDto.CommonEventDto> mergeEvent(List<CustomEvent> customEvents, Event event) {
        List<CustomEventResponseDto.CommonEventDto> commonEventDtos = new ArrayList<>();

        // Event가 null이 아닌 경우에만 추가
        if (event != null) {
            commonEventDtos.add(CustomEventResponseDto.CommonEventDto.fromEvent(event));
        }

        // CustomEvent 중 null이 아닌 것들을 CommonEventDto로 변환하여 추가
        List<CustomEventResponseDto.CommonEventDto> customEventDtos = customEvents.stream()
                .filter(ce -> ce != null) // null인 CustomEvent는 필터링
                .map(CustomEventResponseDto.CommonEventDto::fromCustomEvent)
                .collect(Collectors.toList());

        commonEventDtos.addAll(customEventDtos);

        return commonEventDtos;
    }


    @Transactional(readOnly = true)
    public CustomEventResponseDto.readCustomDto readCustomEvent(Long userId, Long eventId) {
        User user = userRepository.findById(userId).orElseThrow(()->{
            throw new RestApiException(UserErrorCode.INACTIVE_USER);
        });

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

    @Transactional
    public void patchMemo(Long eventId, CustomEventRequestDto.memoDto memoDto) {
        CustomEvent customEvent = customEventRepository.findById(eventId).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_CUSTOM_EVENT);
        });

        customEvent.updateMemo(memoDto.getMemo());

        customEventRepository.save(customEvent);
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

