package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.recordpresent.converter.RecordPresentConverter;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.example.umchackathonr.exception.errorCode.UserErrorCode;
import com.example.umchackathonr.exception.exception.RestApiException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordPresentService {

    private final RecordPresentRepository recordPresentRepository;

    private final UserRepository userRepository;

    public RecordPresentResponseDto.RecordPresentDto readRecordPresent(Long id) {

        RecordPresent recordPresent = recordPresentRepository.findById(id).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_PRESENT);
        });

        return RecordPresentConverter.toRecordPresentDto(recordPresent);
    }

    public RecordPresentResponseDto.RecordPresentListDto readRecordAllPresent(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_USER);
        });

        List<RecordPresent> recordPresents = recordPresentRepository.findAllByUser(user);
        return RecordPresentConverter.toRecordPresentListDto(recordPresents);
    }
}
