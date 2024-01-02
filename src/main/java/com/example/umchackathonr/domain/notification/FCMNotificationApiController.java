package com.example.umchackathonr.domain.notification;

import com.example.umchackathonr.domain.notification.dto.FCMNotificationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/notification")
public class FCMNotificationApiController {

  private final FCMNotificationService fcmNotificationService;

  @PostMapping("now")
  public String sendDailyNotificationByToken(@RequestBody FCMNotificationRequestDto requestDto){
    return fcmNotificationService.sendNotificationByToken(requestDto);
  }


}
