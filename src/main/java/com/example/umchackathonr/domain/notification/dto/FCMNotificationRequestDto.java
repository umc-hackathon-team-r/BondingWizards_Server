package com.example.umchackathonr.domain.notification.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FCMNotificationRequestDto {

  public Long getTargetUserId() {
    return targetUserId;
  }

  public void setTargetUserId(Long targetUserId) {
    this.targetUserId = targetUserId;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getBody() {
    return body;
  }

  public void setBody(String body) {
    this.body = body;
  }

  private Long targetUserId;
  private String title;
  private String body;

  @Builder
  public FCMNotificationRequestDto(Long targetUserId, String title, String body){
    this.targetUserId = targetUserId;
    this.title = title;
    this.body = body;
  }

}
