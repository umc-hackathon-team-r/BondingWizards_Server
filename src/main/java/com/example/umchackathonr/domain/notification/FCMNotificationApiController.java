package com.example.umchackathonr.domain.notification;

import com.example.umchackathonr.domain.notification.dto.FCMNotificationRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
@Tag(name = "FCM", description = "FCM 푸시 알림")
public class FCMNotificationApiController {

  private final FCMNotificationService fcmNotificationService;

  @PostMapping("now")
  @Operation(summary = "사용자 입력 푸시 알림", description = "사용자가 입력한 데이터를 기반으로 푸시 알림을 생성합니다")
  @ApiResponse(responseCode = "400", description = "User is inactive")
  @ApiResponse(responseCode = "500", description = "서버 내 오류")
  public ResponseEntity<String> sendDailyNotificationByToken(@RequestBody FCMNotificationRequestDto requestDto){
    return fcmNotificationService.sendNotificationByToken(requestDto);
  }


}
