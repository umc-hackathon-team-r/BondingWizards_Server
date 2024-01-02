package com.example.umchackathonr.domain.customEvent.controller;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.customEvent.service.CustomEventService;
import com.example.umchackathonr.domain.recordpresent.RecordPresent;
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
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "사용자가 입력하는 Event", description = "사용자가 입력하는 이벤트로 새해,추석 등 명절 등을 제외하고 개인적인 기념일 관련 ")
public class CustomEventController {

    private final CustomEventService customEventService;

    // 이벤트 생성
    @PostMapping("/{userId}/event")
    @Operation(summary = "사용자 입력 이벤트 ", description = "사용자마다 개인적인 기념일을 입력합니다.")
    public ResponseEntity<?> creatCustomEvent
    (@RequestBody CustomEventRequestDto.creatCustomEventDto creatCustomEventDto,
     @RequestParam (defaultValue = "1") Long userId

    ){
        customEventService.creatCustomEvent(creatCustomEventDto ,userId);
        return null;
    }

    // 날짜별 이벤트 목록 조회



    // 이벤트 상세 조회
    @GetMapping("/{userId}/event/{eventId}")
    @Operation(summary = "이벤트 상세 조회 ", description = "이벤트를 단건으로 조회합니다.")
    @ApiResponse(responseCode = "200", description = "이벤트 단건 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RecordPresentResponseDto.RecordPresentDto.class))
    })
    @ApiResponse(responseCode = "403", description = "User is inactive")
    @ApiResponse(responseCode = "404", description = "존재하지 않는 이벤트입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<CustomEventResponseDto.readCustomDto> readCustomEvent(@PathVariable Long userId, @PathVariable Long eventId){
        CustomEventResponseDto.readCustomDto r = customEventService.readCustomEvent(userId ,eventId);
        return ResponseEntity.ok(r);
    }

    @PatchMapping("/api/{eventId}")
    @Operation(summary = "메모 작성 및 수정", description = "메모 작성 및 수정")
    @ApiResponse(responseCode = "200", description = "선물 기록 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = RecordPresentResponseDto.RecordPresentDto.class))
    })
    @ApiResponse(responseCode = "404", description = "존재하지 않는 이벤트입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<String> patchMemo(@PathVariable Long eventId, @RequestBody CustomEventRequestDto.memoDto memoDto){
        customEventService.patchMemo(eventId, memoDto);
        return ResponseEntity.ok("메모 수정이 완료되었습니다.");
    }

}
