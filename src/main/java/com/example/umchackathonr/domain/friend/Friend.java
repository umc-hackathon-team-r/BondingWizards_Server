package com.example.umchackathonr.domain.friend;


import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.event.Event;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Friend extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "friend_id")
    private Long id;

    private String name;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "friend", fetch = FetchType.LAZY)
    private List<Event> events = new ArrayList<>();
}
