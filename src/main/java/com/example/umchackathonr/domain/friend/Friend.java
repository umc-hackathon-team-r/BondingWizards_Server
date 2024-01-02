package com.example.umchackathonr.domain.friend;


import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.customEvent.CustomEvent;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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
    private List<CustomEvent> events = new ArrayList<>();

    public void updateFriendInformation(String name, LocalDate birthday) {
        this.name = name;
        this.birthday = birthday;
    }
}
