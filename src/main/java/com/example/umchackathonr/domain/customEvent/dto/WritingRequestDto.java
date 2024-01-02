package com.example.umchackathonr.domain.customEvent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WritingRequestDto {
    private String goal;
    private List<String> propensity;
}
