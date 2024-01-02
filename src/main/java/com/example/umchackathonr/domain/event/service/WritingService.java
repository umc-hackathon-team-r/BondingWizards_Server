package com.example.umchackathonr.domain.event.service;

import com.example.umchackathonr.domain.event.dto.WritingRequestDto;
import com.example.umchackathonr.domain.event.dto.WritingResponseDto;
import io.github.flashvayne.chatgpt.service.ChatgptService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WritingService {

    // chatGPT가 제공하는 라이브러리
    private final ChatgptService chatgptService;

    public WritingResponseDto getGhatGptWrite(WritingRequestDto writingRequestDto){

        String propensity = removeBrackets(writingRequestDto.getPropensity());
        String prompt = writingRequestDto.getGoal()+"에 보내야 할 글인데 "+ propensity + "특성을 반영해서 짧은 글을 추천해줘";
        // 실제 chatGPT api인데 횟수 제한이 있어서 주석해놓았습니다.
         String message = chatgptService.sendMessage(prompt);
        return new WritingResponseDto(message);
    }

    private String removeBrackets(List<String> propensity) {
        StringBuilder result = new StringBuilder();
        for (String prop : propensity) {
            result.append(prop).append(", ");
        }

        // Remove the trailing comma and space
        if (result.length() > 1) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }
}
