package com.example.umchackathonr.domain;

import com.example.umchackathonr.domain.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class User extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "user")
    List<Friend> friends = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<Event> events = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    List<RecordPresent> recordPresents = new ArrayList<>();
}
