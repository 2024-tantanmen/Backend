package com.tantanmen.carbofootprint.domain.recommend.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.recommend.converter.RecommendConverter;
import com.tantanmen.carbofootprint.domain.recommend.entity.Food;
import com.tantanmen.carbofootprint.domain.recommend.service.RecommendQueryService;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 음식 추천 기능 요청 API
 */

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class RecommendRestController {

	private final RecommendQueryService recommendQueryService;

	@PostMapping("recommend")
	public ApiResponse<RecommendResponseDto.RecommendFoodResponseDto> recommendFoods(@RequestBody RecommendRequestDto.RecommendFoodRequestDto request){
		log.info(request.toString());
		List<Food> recommendFoodList = recommendQueryService.recommendFoods(request);
		RecommendResponseDto.RecommendFoodResponseDto result = RecommendConverter.toRecommendFoodResponseDto(
			recommendFoodList, request.getAllergen_list(), request.getPreference_list());
		return ApiResponse.onSuccess(result);
	}
}
