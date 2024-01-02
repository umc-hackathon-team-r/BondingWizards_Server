package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.recordpresent.converter.RecordPresentConverter;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentRequestDto;
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
@Transactional
public class RecordPresentService {

    private final RecordPresentRepository recordPresentRepository;

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public RecordPresentResponseDto.RecordPresentDto readRecordPresent(Long id) {

        RecordPresent recordPresent = recordPresentRepository.findById(id).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_PRESENT);
        });

        return RecordPresentConverter.toRecordPresentDto(recordPresent);
    }

    @Transactional(readOnly = true)
    public RecordPresentResponseDto.RecordPresentListDto readRecordAllPresent(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_USER);
        });

        List<RecordPresent> recordPresents = recordPresentRepository.findAllByUser(user);
        return RecordPresentConverter.toRecordPresentListDto(recordPresents);
    }

    public Long createPresent(RecordPresentRequestDto.RecordPresentAddReq recordReq) {
        User user = userRepository.findById(recordReq.getUserId()).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_USER);
        });
        RecordPresent recordPresent = RecordPresentConverter.toRecordPresent(recordReq);
        recordPresent.addUser(user);
        RecordPresent recordPresent2 = recordPresentRepository.save(recordPresent);

        return recordPresent2.getId();
    }

    public void deletePresent(Long id) {
        RecordPresent recordPresent = recordPresentRepository.findById(id).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_PRESENT);
        });
        recordPresentRepository.delete(recordPresent);
    }

    public RecordPresentResponseDto.RecordPresentDto updatePresent(Long id, RecordPresentRequestDto.RecordPresentUpdateReq recordPresentUpdateReq) {

        RecordPresent recordPresent = recordPresentRepository.findById(id).orElseThrow(() -> {
            throw new RestApiException(UserErrorCode.INACTIVE_PRESENT);
        });

        RecordPresent r = RecordPresent.builder()
                            .title(recordPresentUpdateReq.getTitle())
                            .id(recordPresent.getId())
                            .description(recordPresentUpdateReq.getDescription())
                            .price(recordPresentUpdateReq.getPrice())
                            .picture(recordPresentUpdateReq.getPicture())
                            .name(recordPresentUpdateReq.getName())
                            .category(recordPresentUpdateReq.getCategory())
                            .user(recordPresent.getUser())
                            .build();

        recordPresentRepository.save(r);

        return RecordPresentConverter.toRecordPresentDto(recordPresent);
    }


}
