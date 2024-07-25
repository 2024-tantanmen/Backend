package com.tantanmen.carbofootprint.domain.classification.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.classification.convertor.ClassificationConvertor;
import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.service.ClassificationService;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationRequestDto;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationResponseDto;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import jakarta.validation.Valid;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/classification")
@RequiredArgsConstructor
public class ClassificationRestController {
	private final ClassificationService classificationService;

	/**
	 * 음식 사진 분석 AI
	 */
	@PostMapping("")
	public ApiResponse<ClassificationResponseDto.FoodClassificationResponseDto> foodClassification(@Valid @ModelAttribute
		ClassificationRequestDto.FoodClassificationRequestDto request){

		Food food = classificationService.foodClassification(request);

		// food entity => dto 변환
		ClassificationResponseDto.FoodClassificationResponseDto result = ClassificationConvertor.toFoodClassificationResponseDto(
			food);

		return ApiResponse.onSuccess(result);
	}
}
