package com.tantanmen.carbofootprint.domain.chatbot.web.controller;

import com.tantanmen.carbofootprint.domain.chatbot.service.ChatbotService;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotResponseDto;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationResponseDto;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "챗봇 관련 API", description = "챗봇 관련 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chatbot")
@Slf4j
public class ChatbotController {

    private final ChatbotService chatbotService;

    // chatbot get question from user
    @Operation(summary = "챗봇 질문 요청 API", description = "챗봇 질문 요청 API")
    @ApiResponses(value = {
        @io.swagger.v3.oas.annotations.responses.ApiResponse(
            responseCode = "COMMON200",
            description = "요청 성공",
            content = {
                @Content(
                    schema = @Schema(
                        implementation = ChatbotResponseDto.Response.class
                    )
                )
            }
        )
    })
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
