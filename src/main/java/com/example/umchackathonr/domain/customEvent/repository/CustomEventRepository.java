package com.example.umchackathonr.domain.customEvent.repository;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomEventRepository extends JpaRepository<CustomEvent,Long> {
    List<CustomEvent> findCustomEventByDateAndUser(LocalDate date, User user);

    List<CustomEvent> findCustomEventByDate(LocalDate date);

}
