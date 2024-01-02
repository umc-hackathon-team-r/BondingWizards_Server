package com.example.umchackathonr.domain.recordpresent;


import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.preferpresent.PreferPresent;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "record_present")
@Getter
public class RecordPresent extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_present_id")
    private Long id;

    private String title;

    private String picture;

    private String description;

    private String category;

    private int price;

    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "recordPresent", fetch = FetchType.LAZY)
    private List<PreferPresent> preferPresents = new ArrayList<>();
}
