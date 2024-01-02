package com.example.umchackathonr.domain.recordpresent;


import com.example.umchackathonr.domain.user.User;
import com.example.umchackathonr.domain.preferpresent.PreferPresent;
import com.example.umchackathonr.global.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
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

    public void addUser(User user) {
        this.user = user;
    }
}