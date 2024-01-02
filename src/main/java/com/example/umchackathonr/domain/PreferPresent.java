package com.example.umchackathonr.domain;


import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "prefer_present")
@Getter
public class PreferPresent extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "prefer_present_id")
    private Long id;

    private int price;

    private String picture;

    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_present_id")
    private RecordPresent recordPresent;
}
