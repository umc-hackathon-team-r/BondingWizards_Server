package com.example.umchackathonr.domain.customEvent.controller;

import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.customEvent.dto.CustomEventResponseDto;
import com.example.umchackathonr.domain.customEvent.service.CustomEventService;

import com.example.umchackathonr.domain.friend.dto.CreateFriendResponse;
import com.example.umchackathonr.domain.friend.dto.FriendResponse;
import com.example.umchackathonr.domain.recordpresent.dto.RecordPresentResponseDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.time.LocalDate;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Tag(name = "Event", description = "사용자가 이벤트를 입력과 새해,추석 등 기념일 포함 ")
public class CustomEventController {

    private final CustomEventService customEventService;

    // 이벤트 생성
    @PostMapping("/{userId}/event")
    @Operation(summary = "사용자 입력 이벤트 ", description = "사용자마다 개인적인 기념일을 입력합니다.")
    @ApiResponse(responseCode = "201", description = "이벤트 생성 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CustomEventResponseDto.CreateCustomEventResponse.class))
    })
    @ApiResponse(responseCode = "403", description = "없는 유저입니다.")
    public ResponseEntity<?> creatCustomEvent
    (@RequestBody CustomEventRequestDto.creatCustomEventDto creatCustomEventDto,
     @RequestParam (defaultValue = "1") Long userId

    ){
        Long id = customEventService.creatCustomEvent(creatCustomEventDto, userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomEventResponseDto.CreateCustomEventResponse(id));
    }

    // 날짜별 이벤트 목록 조회
    @GetMapping("/{userId}/event")
    @Operation(summary = "이벤트 목록 조회 ", description = "모든 기념일을 조회합니다.")
    @ApiResponse(responseCode = "201", description = "이벤트 목록 조회 성공", content = {
            @Content(mediaType = "application/json", schema = @Schema(implementation = CustomEventResponseDto.ListEventResponseDto.class))
    })
    @ApiResponse(responseCode = "403", description = "없는 유저입니다.")
    public ResponseEntity<?> getEventsByDate(
            @PathVariable("userId") Long userId, @RequestParam("date") LocalDate date) {
        CustomEventResponseDto.ListEventResponseDto listCustomEvent = customEventService.getListCustomEvent(date,userId);
        return ResponseEntity.ok(listCustomEvent);
    }

  // 이벤트 수정
  @PatchMapping("/event/{eventId}")
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
  @DeleteMapping("/event/{eventId}")
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

