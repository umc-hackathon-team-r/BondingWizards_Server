package com.example.umchackathonr.domain.event.controller;

import com.example.umchackathonr.domain.event.service.WritingService;
import com.example.umchackathonr.domain.event.dto.WritingRequestDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event/writing")
@Tag(name = "chatGPT API", description = "chatGPT로 글 추천 API ")
public class WritingController {
    private final WritingService writingService;

    @PostMapping("")
    @Operation(summary = "글 추천", description = "목적과 성향기반으로 글을 추천합니다.")
    public ResponseEntity<?> createGhatGptWrite(@RequestBody WritingRequestDto writingRequestDto){
       return ResponseEntity.ok(writingService.getGhatGptWrite(writingRequestDto));
    }
}
