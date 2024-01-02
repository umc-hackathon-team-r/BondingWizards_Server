package com.example.umchackathonr.domain.notification;

import com.example.umchackathonr.domain.customEvent.EventRepository;
import com.example.umchackathonr.domain.notification.dto.FCMNotificationRequestDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

  private final FirebaseMessaging firebaseMessaging;
  private final UserRepository userRepository;
 // private final EventRepository eventRepository;

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

  //유저가 입력 하면 알림 송신
  @Scheduled(cron = "0 0 12 * * ?") // 매일 12시에 실행
  public void sendDailyNotifications() {
    List<User> users = userRepository.findAll(); // 모든 유저를 조회

    for(User user : users) {
      if(user.getFirebaseToken() != null) {
        FCMNotificationRequestDto requestDto = new FCMNotificationRequestDto();
        requestDto.setTargetUserId(user.getId()); // User 클래스에 getId() 메소드가 있다고 가정
        requestDto.setTitle("알림 제목");
        requestDto.setBody("알림 내용");

        sendNotificationByToken(requestDto); // 각 유저에게 알림을 보냄
      }
    }
  }

  //고정 event 알림 송신
//  @Scheduled(cron = "0 0 9 * * ?")
//  public void sendEventNotifications(){
//    Date today = new Date();
//  }

}
