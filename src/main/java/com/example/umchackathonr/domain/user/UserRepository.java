package com.example.umchackathonr.domain.user;

import com.example.umchackathonr.domain.friend.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
