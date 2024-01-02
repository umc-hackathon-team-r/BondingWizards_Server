package com.example.umchackathonr.domain.friendBirthday;

import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.userEventNotification.NotificationFrequency;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;

@Entity
@Getter
public class FriendBirthday extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String friendName;

  private LocalDate date;

  @Enumerated(EnumType.STRING)
  private NotificationFrequency notificationFrequency;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User user;

}
