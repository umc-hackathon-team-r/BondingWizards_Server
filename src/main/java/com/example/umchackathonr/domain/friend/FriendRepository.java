package com.example.umchackathonr.domain.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    boolean existsByNameAndBirthday(String name, LocalDate birthday);
}
