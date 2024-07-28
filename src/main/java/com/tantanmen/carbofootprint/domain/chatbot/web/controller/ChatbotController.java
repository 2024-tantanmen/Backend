package com.tantanmen.carbofootprint.domain.chatbot.web.controller;

import com.tantanmen.carbofootprint.domain.chatbot.service.ChatbotService;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotResponseDto;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
@Slf4j
public class ChatbotController {

    private final ChatbotService chatbotService;

    // chatbot get question from user
    @PostMapping("")
    public ApiResponse<ChatbotResponseDto.Response> initialChatbot(@RequestBody ChatbotRequestDto.Request request) throws Exception {
        log.info("question : {}", request.getQuestion());
        log.info("isRecommend : {}", request.isRecommend());
        String response = chatbotService.getResponse(request);

        ChatbotResponseDto.Response result = ChatbotResponseDto.Response.builder()
            .answer(response)
            .build();

        return ApiResponse.onSuccess(result);
    }
}
