package com.example.umchackathonr.domain.event.controller;

import com.example.umchackathonr.domain.event.service.WritingService;
import com.example.umchackathonr.domain.event.dto.WritingRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/event/writing")
public class WritingController {
    private final WritingService writingService;

    @PostMapping("")
    public ResponseEntity<?> createGhatGptWrite(@RequestBody WritingRequestDto writingRequestDto){
       return ResponseEntity.ok(writingService.getGhatGptWrite(writingRequestDto));
    }
}
