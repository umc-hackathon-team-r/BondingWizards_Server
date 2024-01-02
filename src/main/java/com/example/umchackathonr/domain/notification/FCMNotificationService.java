package com.example.umchackathonr.domain.notification;

import com.example.umchackathonr.domain.notification.dto.FCMNotificationRequestDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

  private final FirebaseMessaging firebaseMessaging;
  private final UserRepository userRepository;

  public String sendNotificationByToken(FCMNotificationRequestDto requestDto){
    Optional<User> user = userRepository.findById(requestDto.getTargetUserId());

    if(user.isPresent()){
      if(user.get().getFirebaseToken()!=null){
        Notification notification = Notification.builder()
            .setTitle(requestDto.getTitle())
            .setBody(requestDto.getBody())
            .build();

        Message message = Message.builder()
            .setToken(user.get().getFirebaseToken())
            .setNotification(notification)
            .build();

        try{
          firebaseMessaging.send(message);
          return "알림을 성공적으로 전송했습니다. targetUserId=" + requestDto.getTargetUserId();
        } catch (FirebaseMessagingException e){
          e.printStackTrace();
          return "알림 보내기를 실패하였습니다. targetUserId=" + requestDto.getTargetUserId();
        }

      } else {
        return "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId="
            + requestDto.getTargetUserId();
      }
    } else {
      return "해당 유저가 존재하지 않습니다. targetUserId-" + requestDto.getTargetUserId();
    }
  }


}