package com.example.umchackathonr.domain.notification;


import com.example.umchackathonr.domain.Event.Event;
import com.example.umchackathonr.domain.Event.EventRepository;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.customEvent.repository.CustomEventRepository;
import com.example.umchackathonr.domain.notification.dto.FCMNotificationRequestDto;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.user.UserRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class FCMNotificationService {

  private final FirebaseMessaging firebaseMessaging;
  private final UserRepository userRepository;
  private final EventRepository eventRepository;
  private final CustomEventRepository customEventRepository;
  public ResponseEntity<Map<String, String>> sendNotificationByToken(FCMNotificationRequestDto requestDto){
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
          Map<String, String> response = new HashMap<>();
          response.put("status", "알림을 성공적으로 전송했습니다. targetUserId=" + requestDto.getTargetUserId());
          response.put("title", requestDto.getTitle());
          response.put("body", requestDto.getBody());
          return ResponseEntity.ok(response);
        } catch (FirebaseMessagingException e){
          e.printStackTrace();
          return ResponseEntity.badRequest().body(
              Collections.singletonMap("status", "알림 보내기를 실패하였습니다. targetUserId=" + requestDto.getTargetUserId()));
        }
      } else {
        return ResponseEntity.badRequest().body(Collections.singletonMap("status", "서버에 저장된 해당 유저의 FirebaseToken이 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId()));
      }
    } else {
      return ResponseEntity.badRequest().body(Collections.singletonMap("status", "해당 유저가 존재하지 않습니다. targetUserId=" + requestDto.getTargetUserId()));
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
  @Scheduled(cron = "0 0 9 * * ?")
  public void sendEventNotifications(){
    LocalDate today = LocalDate.now(); // 오늘 날짜
    List<Event> events = eventRepository.findByDate(today); // 오늘 날짜의 모든 일정 조회

    for(Event event : events) {
      List<User> users = userRepository.findAll(); // 모든 유저를 조회

      for(User user : users) {
        if(user.getFirebaseToken() != null) {
          FCMNotificationRequestDto requestDto = new FCMNotificationRequestDto();
          requestDto.setTargetUserId(user.getId());
          requestDto.setTitle("기념일 알림");
          requestDto.setBody(event.getNotification()); // 일정의 제목을 알림 내용으로 설정

          sendNotificationByToken(requestDto); // 각 유저에게 알림을 보냄
        }
      }
    }
  }

  //Customevent 알림 송신
  @Scheduled(cron = "0 0 9 * * ?")
  public void sendCustomEventNotifications(){
    LocalDate today = LocalDate.now(); // 오늘 날짜
    List<CustomEvent> events = customEventRepository.findCustomEventByDate(today); // 오늘 날짜의 모든 일정 조회

    for(CustomEvent event : events) {
      List<User> users = userRepository.findAll(); // 모든 유저를 조회

      for(User user : users) {
        if(user.getFirebaseToken() != null) {
          FCMNotificationRequestDto requestDto = new FCMNotificationRequestDto();
          requestDto.setTargetUserId(user.getId());
          requestDto.setTitle("오늘은");
          requestDto.setBody(event.getTitle()+"입니다. 축하의 메시지와 선물을 보내보세요!"); // 일정의 제목을 알림 내용으로 설정

          sendNotificationByToken(requestDto); // 각 유저에게 알림을 보냄
        }
      }
    }
  }

}
