package com.example.umchackathonr.domain;

import com.example.umchackathonr.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Friend extends BaseEntity  {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "friend_id")
    private Long id;

    private String name;

    private LocalDate birthday;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "friend")
    private List<Event> events = new ArrayList<>();
}
