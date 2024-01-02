package com.example.umchackathonr.domain.event.service;

import com.example.umchackathonr.domain.event.dto.WritingRequestDto;
import com.example.umchackathonr.domain.event.dto.WritingResponseDto;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WritingService {

    // chatGPT가 제공하는 라이브러리
    private final ChatgptService chatgptService;

    public WritingResponseDto getGhatGptWrite(WritingRequestDto writingRequestDto){
        String prompt = writingRequestDto.getGoal()+"에 보내기 위한"+ writingRequestDto.getPropensity() + "성향을 반영해서 글을 추천해줘";
        String message = chatgptService.sendMessage(prompt);
        return new WritingResponseDto(message);
    }
}
