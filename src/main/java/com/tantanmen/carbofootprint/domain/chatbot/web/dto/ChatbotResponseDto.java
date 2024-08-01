package com.tantanmen.carbofootprint.domain.chatbot.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ChatbotResponseDto {

    @Getter
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response{
        @Schema(description = "챗봇 응답 내용", example = "건강한 탄수화물은 우리의 에너지원으로 중요한 역할을 합니다. 통곡물, 과일, 채소, 콩류 등이 좋은 선택입니다. 이들은 섬유소가 풍부하고 인슐린 수치를 안정적으로 유지하는 데 도움을 줍니다. 예를 들어, 오트밀, 퀴노아, 브로콜리, 사과 등이 있습니다. 건강한 탄수화물을 섭취해 에너지를 유지하세요!")
        private String answer;
    }

}

