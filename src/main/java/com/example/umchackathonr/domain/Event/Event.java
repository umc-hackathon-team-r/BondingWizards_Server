package com.example.umchackathonr.domain.Event;

import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private LocalDate date;

    private String title;

}
