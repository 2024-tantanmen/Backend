package com.tantanmen.carbofootprint.domain.chatbot.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tantanmen.carbofootprint.domain.chatbot.web.dto.ChatbotRequestDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;

@Service
@Slf4j
public class ChatbotServiceImpl implements ChatbotService {

    @Value("${gpt.key.apiKey}")
    private String apiKey;

    @Value("${gpt.model.chatbot}")
    private String model;

    @Override
    public String getResponse(ChatbotRequestDto.Request request) throws Exception {
        String prompt = "";
        String getAnswer = "";

        if(request.isRecommend()){
            prompt = setRecommendPrompt();
            log.info("This is Recommend question : {}", prompt);
        }
        else{
            prompt = setPromptAboutInfo(request.getQuestion());
            log.info("This is PromptAboutInfo question : {}", prompt);
        }

        WebClient client = WebClient.builder()
                .baseUrl("https://api.openai.com")
                .build();

        try{
            Mono<String> response = client.post()
                    .uri("/v1/chat/completions")
                    .header("Authorization", "Bearer " + apiKey)
                    .header("Content-Type", "application/json")
                    .bodyValue(prompt)
                    .retrieve()
                    .bodyToMono(String.class);

            String responseBody = response.block();

            if (responseBody == null) {
                throw new RuntimeException("Response body is null");
            }

            log.info("Response : {}", responseBody);

            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(responseBody);

            JsonNode choicesNode = rootNode.path("choices");
            JsonNode messageNode = choicesNode.get(0).path("message");

            getAnswer = messageNode.path("content").asText();

            log.info("getAnswer : {}", getAnswer);
        }
        catch (WebClientResponseException e){
            e.printStackTrace();

            throw new Exception("Chatbot Error!");
        }

        return getAnswer;
    }

    @Override
    public String setPromptAboutInfo(String question) {
        String prompt = "";

        prompt += "{"
                + "\"model\": \"" + model +"\","
                + "\"messages\": [{\"role\": \"assistant\", \"content\": \"" + "너는 탄수화물 중독 관리 서비스의 챗봇이야. 모든 질문에 대한 답변을 챗봇 형식으로 간단하게 300자 이내로 정리해서 알려줘. (과일도 탄수화물이야? 라는 질문에 대한 예시 :  네, 건강식품으로 꼽히는 과일도 탄수화물 음식의 일종입니다! 탄수화물 함량이 적은 과일로는 [복숭아], [사과], [수박] 등이 있습니다!) 질문은 다음과 같아. " + question + "\"}]"
                + "}";

        return prompt;
    }

    @Override
    public String setRecommendPrompt() {
        String prompt = "";

        prompt += "{"
                + "\"model\": \"" + model +"\","
                + "\"messages\": [{\"role\": \"assistant\", \"content\": \"" + "너는 탄수화물 중독 관리 서비스의 챗봇이야. 탄수화물 중독 위험에서 벗어난 건강한 외식 메뉴 하나 추천해줘. 음식 이름은 [음식이름]으로 [ ]로 감싸서 답해줘. (답변 예시 : 탄수화물 중독 위험에서 벗어난 건강한 외식 메뉴를 추천해드리겠습니다! [그릴드 연어와 아스파라거스]는 어떠신가요?)" + "\"}]"
                + "}";

        return prompt;
    }
}
