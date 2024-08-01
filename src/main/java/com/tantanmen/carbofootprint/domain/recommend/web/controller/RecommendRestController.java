package com.tantanmen.carbofootprint.domain.recommend.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.recommend.converter.RecommendConverter;
import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.service.RecommendCommandService;
import com.tantanmen.carbofootprint.domain.recommend.service.RecommendQueryService;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendRequestDto;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "음식 추천 API", description = "사용자 맞춤 음식 추천 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/foods")
public class RecommendRestController {

	private final RecommendQueryService recommendQueryService;
	private final RecommendCommandService recommendCommandService;

	/**
	 * 음식 추천 기능 요청 API
	 */
	@Operation(summary = "음식 추천 요청 API", description = "알레르기, 식습관 별 음식 추천 API 요청")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = RecommendResponseDto.RecommendFoodResponseDto.class
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "RECOMMEND4001",
			description = "선택된 알레르기가 없는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "선택된 알레르기가 존재하지 않습니다."
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "RECOMMEND4002",
			description = "선택된 식습관이 없는 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "선택된 식습관이 존재하지 않습니다."
					)
				)
			}
		)
	})
	@PostMapping("recommend")
	public ApiResponse<RecommendResponseDto.RecommendFoodResponseDto> recommendFoods(
		@RequestBody RecommendRequestDto.RecommendFoodRequestDto request, @Parameter(hidden = true) @LoginMember Member member) {
		log.info(request.toString());
		// 음식 추천
		List<FoodRecommend> recommendFoodListRecommend = recommendQueryService.recommendFoods(request);
		RecommendResponseDto.RecommendFoodResponseDto result = RecommendConverter.toRecommendFoodResponseDto(
			recommendFoodListRecommend, request.getAllergen_list(), request.getPreference_list());

		// 만약 사용자가 로그인 한 상태라면, 결과 데이터 저장
		if(member != null){
			recommendCommandService.saveResultData(member, recommendFoodListRecommend, request);
		}

		return ApiResponse.onSuccess(result);
	}
}
