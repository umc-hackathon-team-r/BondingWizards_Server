package com.example.umchackathonr.domain.Event;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface EventRepository extends JpaRepository<Event,Long> {

    Event findEventByDate(LocalDate date);
    List<Event> findByDate(LocalDate date);

}
