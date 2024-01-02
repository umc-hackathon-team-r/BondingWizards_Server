package com.example.umchackathonr.domain.customEvent.controller;

import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.customEvent.service.CustomEventService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

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
        return ResponseEntity.ok().build();
    }

    // 날짜별 이벤트 목록 조회
    @GetMapping("/{userId}/event")
    @Operation(summary = "사용자가 입력한 이벤트 목록 조회 ", description = "사용자마다 개인적인 입력한 기념일을 조회합니다.")
    public ResponseEntity<?> getEventsByDate(
            @PathVariable("userId") Long userId, @RequestParam("date") LocalDate date) {
        CustomEventResponseDto.ListEventResponseDto listCustomEvent = customEventService.getListCustomEvent(date);
        return ResponseEntity.ok(listCustomEvent);
    }

    // 이벤트 상세 조회

    // 이벤트 수정
    @PatchMapping("/api/event/{eventId}")
    @Operation(summary = "이벤트 수정", description = "이벤트 정보를 수정합니다.")
    @ApiResponse(responseCode = "201", description = "이벤트 수정 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "존재하지 않는 이벤트 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> updateEvent(
            @PathVariable Long eventId,
            @Valid @RequestBody CustomEventRequestDto.updateCustomEventDto request) {
        customEventService.update(request, eventId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "수정 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }


    // 이벤트 삭제
    @DeleteMapping("/api/event/{eventId}")
    @Operation(summary = "이벤트 삭제", description = "이벤트 정보를 삭제합니다.")
    @ApiResponse(responseCode = "201", description = "이벤트 삭제 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
    })
    @ApiResponse(responseCode = "403", description = "존재하지 않는 이벤트 ID입니다.")
    @ApiResponse(responseCode = "500", description = "서버 내 오류")
    public ResponseEntity<Map<String, String>> deleteEvent(
            @PathVariable Long eventId) {

        customEventService.delete(eventId);

        Map<String, String> response = new HashMap<>();
        response.put("message", "삭제 완료");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }
}
