package com.example.umchackathonr.domain.user;


import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.friendBirthday.FriendBirthday;
import com.example.umchackathonr.domain.recordpresent.RecordPresent;
import com.example.umchackathonr.domain.userEventNotification.UserEventNotification;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String name;

    private String firebaseToken;

    @OneToMany(mappedBy = "user")
    private List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")

    private List<CustomEvent> events = new ArrayList<>();

    private List<RecordPresent> recordPresents = new ArrayList<>();


    @OneToMany(mappedBy = "user")
    private List<FriendBirthday> friendBirthdays = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<UserEventNotification> userEventNotifications = new ArrayList<>();
}
