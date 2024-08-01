package com.tantanmen.carbofootprint.domain.classification.web.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.classification.convertor.ClassificationConvertor;
import com.tantanmen.carbofootprint.domain.classification.entity.ClassificationResult;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.service.ClassificationService;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationResponseDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "음식 사진 성분 인식 API", description = "사진 내부의 음식 인식 및 성분 분석 API")
@Slf4j
@RestController
@RequestMapping("/api/classification")
@RequiredArgsConstructor
public class ClassificationRestController {
	private final ClassificationService classificationService;

	/**
	 * 음식 사진 분석 AI
	 */
	@Operation(summary = "음식 사진 분석 API 요청", description = "사진 내부의 음식 인식 및 성분 분석 API 요청")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ClassificationResponseDto.FoodClassificationResponseDto.class
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "CLASSIFICATION5001",
			description = "음식 사진 AI 서버 내부 오류 발생",
			content = {
				@Content(
					schema = @Schema(
						example = "음식 사진 AI 요청 중 오류가 발생했습니다."
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "CLASSIFICATION4001",
			description = "인식할 수 없는 음식 사진인 경우 (여러개의 음식이 같이 있거나, 학습되지 않은 사진인 경우)",
			content = {
				@Content(
					schema = @Schema(
						example = "음식 사진 인식에 실패했습니다."
					)
				)
			}
		)
	})
	@PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<ClassificationResponseDto.FoodClassificationResponseDto> foodClassification(
		@Valid @ModelAttribute
		ClassificationRequestDto.FoodClassificationRequestDto request) {

		Food food = classificationService.foodClassification(request);

		// food entity => dto 변환
		ClassificationResponseDto.FoodClassificationResponseDto result = ClassificationConvertor.toFoodClassificationResponseDto(
			food);

		return ApiResponse.onSuccess(result);
	}

	@PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ApiResponse<ClassificationResponseDto.SaveClassificationResultResponseDto> saveClassificationResult(@Valid @ModelAttribute
		ClassificationRequestDto.SaveClassificationResultRequestDto request, @Parameter(hidden = true) @LoginMember Member member){
		ClassificationResult classificationResult = classificationService.saveClassificationResult(request, member);
		ClassificationResponseDto.SaveClassificationResultResponseDto result = ClassificationResponseDto.SaveClassificationResultResponseDto.builder()
			.classification_result_id(classificationResult.getId())
			.build();

		return ApiResponse.onSuccess(result);
	}
}
