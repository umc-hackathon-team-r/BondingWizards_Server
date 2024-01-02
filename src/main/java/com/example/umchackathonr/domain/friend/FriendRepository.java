package com.example.umchackathonr.domain.friend;

import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface FriendRepository extends JpaRepository<Friend, Long> {

    boolean existsByNameAndBirthday(String name, LocalDate birthday);

    Friend findFriendByNameAndBirthday(String name, LocalDate birthday);
}
