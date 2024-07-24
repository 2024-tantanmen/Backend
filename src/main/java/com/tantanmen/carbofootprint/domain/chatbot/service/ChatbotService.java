package com.tantanmen.carbofootprint.domain.chatbot.service;

import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotResponseDto;
import com.tantanmen.carbofootprint.global.entity.response.CustomApiResponse;
import org.springframework.http.ResponseEntity;

public interface ChatbotService {
    ResponseEntity<CustomApiResponse<ChatbotResponseDto.Response>> getResponse(ChatbotRequestDto.Request request) throws Exception;

    String setPromptAboutInfo(String question);

    String setRecommendPrompt();
}
