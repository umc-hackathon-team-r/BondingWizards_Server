package com.example.umchackathonr.domain.customEvent.repository;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CustomEventRepository extends JpaRepository<CustomEvent,Long> {
    List<CustomEvent> findCustomEventByDate(LocalDate date);
}
