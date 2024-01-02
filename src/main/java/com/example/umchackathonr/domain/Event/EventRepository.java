package com.example.umchackathonr.domain.Event;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event,Long> {
    Event findEventByDate(LocalDate date);
}
