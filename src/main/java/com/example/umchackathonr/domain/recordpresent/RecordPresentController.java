package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class RecordPresentController {

    private final RecordPresentService recordPresentService;

    @GetMapping("/present/{presentId}")
    public ResponseEntity<RecordPresentResponseDto.RecordPresentDto> readRecordPresent(@PathVariable Long presentId) {
        RecordPresentResponseDto.RecordPresentDto r = recordPresentService.readRecordPresent(presentId);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/{userId}/present")
    public ResponseEntity<RecordPresentResponseDto.RecordPresentListDto> readAllRecordPresent(@PathVariable Long userId) {
        RecordPresentResponseDto.RecordPresentListDto list = recordPresentService.readRecordAllPresent(userId);
        return ResponseEntity.ok(list);
    }
}
