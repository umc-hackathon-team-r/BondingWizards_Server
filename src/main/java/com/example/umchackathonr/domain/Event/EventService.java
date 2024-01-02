package com.example.umchackathonr.domain.Event;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    @Scheduled(cron = "0 0 0 1 1 ?") // 매년 1월 1일 자정에 실행
    public void createEvent(){
        saveEvent(LocalDate.of(LocalDate.now().getYear() + 1, 1, 1), "새해 이벤트");
        saveEvent(LocalDate.of(LocalDate.now().getYear(), 12, 25), "크리스마스 이벤트");
        saveEvent(LocalDate.of(LocalDate.now().getYear(), 8, 15), "추석 이벤트");
    }
    private void saveEvent(LocalDate date, String title) {
        Event event = new Event();
        event.setDate(date);
        event.setTitle(title);
        eventRepository.save(event);
    }
}
