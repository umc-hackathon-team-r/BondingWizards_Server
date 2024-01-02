package com.example.umchackathonr.domain.event;


import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;

@Entity
@Getter
public class Event extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;


    private LocalDate date;


    private String title;

}
