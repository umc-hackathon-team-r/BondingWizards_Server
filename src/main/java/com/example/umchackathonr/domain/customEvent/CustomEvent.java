package com.example.umchackathonr.domain.customEvent;


import com.example.umchackathonr.domain.customEvent.dto.CustomEventRequestDto;
import com.example.umchackathonr.domain.friend.Friend;
import com.example.umchackathonr.domain.user.User;

import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomEvent extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "event_id")
    private Long id;

    private LocalDate date;

    private String title;

    private String memo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "friend_id")
    private Friend friend;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public void updateEventInformation(CustomEventRequestDto.updateCustomEventDto request) {
        this.title = request.getTitle();
        this.date = request.getDate();
        this.memo = request.getMemo();
    }


}
