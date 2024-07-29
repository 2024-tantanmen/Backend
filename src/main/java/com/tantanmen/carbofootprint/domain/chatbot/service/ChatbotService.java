package com.tantanmen.carbofootprint.domain.chatbot.service;

import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotResponseDto;

public interface ChatbotService {
    String getResponse(ChatbotRequestDto.Request request) throws Exception;

    String setPromptAboutInfo(String question);

    String setRecommendPrompt();
}
