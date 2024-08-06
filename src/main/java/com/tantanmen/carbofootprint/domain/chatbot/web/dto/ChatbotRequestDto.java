package com.tantanmen.carbofootprint.domain.chatbot.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

public class ChatbotRequestDto {

    @Data
    public static class Request {
        private boolean isRecommend;
        @Schema(description = "챗봇 질문 내용", example = "건강한 탄수화물에 대해 설명해줘")
        private String question;
    }
}
