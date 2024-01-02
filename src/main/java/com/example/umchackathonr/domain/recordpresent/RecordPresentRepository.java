package com.example.umchackathonr.domain.recordpresent;

import com.example.umchackathonr.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordPresentRepository extends JpaRepository<RecordPresent, Long> {
    public List<RecordPresent> findAllByUser(User user);
}
