package com.tantanmen.carbofootprint.domain.chatbot.web.dto;

import lombok.*;

public class ChatbotRequestDto {

    @Data
    public static class Request {
        private boolean isRecommend;
        private String question;
    }
}
