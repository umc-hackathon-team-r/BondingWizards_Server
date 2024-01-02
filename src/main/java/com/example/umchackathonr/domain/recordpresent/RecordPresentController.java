package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentRequestDto;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/present")
    public ResponseEntity<Long> createPresent(@RequestBody RecordPresentRequestDto.RecordPresentAddReq recordPresentAddReq) {
        Long record_present_id = recordPresentService.createPresent(recordPresentAddReq);
        return ResponseEntity.ok(record_present_id);
    }
}
