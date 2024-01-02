package com.example.umchackathonr.domain.customEvent.repository;

import com.example.umchackathonr.domain.customEvent.CustomEvent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomEventRepository extends JpaRepository<CustomEvent,Long> {

}
