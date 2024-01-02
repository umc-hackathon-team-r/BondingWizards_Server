package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.friend.dto.CreateFriendResponse;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentRequestDto;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "RecordPresent", description = "선물 기록 관련 API")
public class RecordPresentController {

    private final RecordPresentService recordPresentService;

    @GetMapping("/present/{presentId}")
    @Operation(summary = "선물 기록 상세 조회", description = "선물 기록을 단건으로 조회합니다..")
    @ApiResponse(responseCode = "200", description = "선물 기록 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RecordPresentResponseDto.RecordPresentDto.class))
    })
    @ApiResponse(responseCode = "404", description = "존재하지 않는 선물입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<RecordPresentResponseDto.RecordPresentDto> readRecordPresent(@PathVariable Long presentId) {
        RecordPresentResponseDto.RecordPresentDto r = recordPresentService.readRecordPresent(presentId);
        return ResponseEntity.ok(r);
    }

    @GetMapping("/{userId}/present")
    @Operation(summary = "선물 기록 목록 조회", description = "선물 기록을 전체로 조회합니다..")
    @ApiResponse(responseCode = "200", description = "선물 기록 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RecordPresentResponseDto.RecordPresentListDto.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<RecordPresentResponseDto.RecordPresentListDto> readAllRecordPresent(@PathVariable Long userId) {
        RecordPresentResponseDto.RecordPresentListDto list = recordPresentService.readRecordAllPresent(userId);
        return ResponseEntity.ok(list);
    }

    @PostMapping("/present")
    @Operation(summary = "선물 기록 생성", description = "선물 기록을 생성.")
    @ApiResponse(responseCode = "200", description = "선물 기록 생성 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = Long.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Long> createPresent(@RequestBody RecordPresentRequestDto.RecordPresentAddReq recordPresentAddReq) {
        Long record_present_id = recordPresentService.createPresent(recordPresentAddReq);
        return ResponseEntity.ok(record_present_id);
    }

    @DeleteMapping("/present/{presentId}")
    @Operation(summary = "선물 기록 삭제", description = "선물 기록 삭제")
    @ApiResponse(responseCode = "200", description = "선물 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "404", description = "존재하지 않는 선물입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<String> deletePresent(@PathVariable Long presentId) {
        recordPresentService.deletePresent(presentId);
        return ResponseEntity.ok("삭제가 완료 되었습니다.");
    }

    @PatchMapping("/present/{presentId}")
    @Operation(summary = "선물 기록 수정", description = "선물 기록 수정")
    @ApiResponse(responseCode = "200", description = "선물 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RecordPresentResponseDto.RecordPresentDto.class))
    })
    @ApiResponse(responseCode = "404", description = "존재하지 않는 선물입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<RecordPresentResponseDto.RecordPresentDto> updatePresent(@PathVariable Long presentId, @RequestBody RecordPresentRequestDto.RecordPresentUpdateReq recordPresentUpdateReq) {
        RecordPresentResponseDto.RecordPresentDto r = recordPresentService.updatePresent(presentId, recordPresentUpdateReq);
        return ResponseEntity.ok(r);
    }
}
