package com.tantanmen.carbofootprint.domain.classification.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tantanmen.carbofootprint.domain.classification.exception.ClassificationFailException;
import com.tantanmen.carbofootprint.domain.classification.exception.ClassificationRequestErrorException;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 음식 분석 AI 모델을 이용한 성분 분석
 * 음식 분석 모델은 http://localhost:5000
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class FoodClassificationAIServiceImpl implements FoodClassificationAIService {

	private final ObjectMapper objectMapper;

	@Value("${food.classification.ai.uri}")
	private String FOOD_CLASSIFICATION_URI;

	/**
	 * 음식 분석 후 데이터에 저장 된 음식 코드로 반환
	 */
	@Override
	public String detectFood(MultipartFile image) {
		// TODO URL주소 localhost 로 변경, 사용자 id값 전송 추가
		RestTemplate restTemplate = new RestTemplate();

		// 헤더 설정
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		// 바디 설정
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		// TODO 사용자 ID 값으로 변경하기
		body.add("user_seq", 1);
		body.add("user_img", image.getResource());

		// HTTP 객체 생성
		HttpEntity entity = new HttpEntity(body, headers);

		log.info(entity.toString());
		// HTTP 요청
		ResponseEntity<String> response = restTemplate.exchange(FOOD_CLASSIFICATION_URI, HttpMethod.POST, entity,
			String.class);

		// 반환된 상태코드가 올바르지 못한 경우
		if (response.getStatusCode() != HttpStatusCode.valueOf(200)) {
			log.error("음식 사진 AI 요청 중 오류가 발생했습니다.");
			throw new ClassificationRequestErrorException();
		}

		String code;
		// json 객체 바인딩
		try {
			JsonNode jsonNode = objectMapper.readTree(response.getBody());
			// 응답 message가 성공하지 못한 경우
			if (!jsonNode.path("message").asText().equals("success")) {
				log.error("음식 사진 AI 요청 중 오류가 발생했습니다.");
				throw new ClassificationRequestErrorException();
			}
			code = jsonNode.path("code").asText();
		} catch (Exception e) {
			log.error("JSON 객체 변환 중 오류가 발생했습니다.");
			throw new GeneralException(ErrorStatus._INTERNAL_SERVER_ERROR);
		}
		// 음식 인식에 실패 한 경우
		if (code.equals("null") || code.equals("00000000")) {
			log.error("음식 사진 인식에 실패했습니다.");
			throw new ClassificationFailException();
		}
		return code;
	}
}
