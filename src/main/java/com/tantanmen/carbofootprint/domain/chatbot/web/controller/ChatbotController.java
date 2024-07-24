package com.tantanmen.carbofootprint.domain.chatbot.web.controller;

import com.tantanmen.carbofootprint.domain.chatbot.service.ChatbotService;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotResponseDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
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
    public ResponseEntity<CustomApiResponse<ChatbotResponseDto.Response>> InitialChatbot(@RequestBody ChatbotRequestDto.Request request) throws Exception {

        log.info("=== This is Controller ===");
        log.info("question : {}", request.getQuestion());
        log.info("isRecommend : {}", request.isRecommend());
        ResponseEntity<CustomApiResponse<ChatbotResponseDto.Response>> response = chatbotService.getResponse(request);

        return response;
    }
}
