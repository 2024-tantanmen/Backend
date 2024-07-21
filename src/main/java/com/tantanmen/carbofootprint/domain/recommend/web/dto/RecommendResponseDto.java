package com.tantanmen.carbofootprint.domain.recommend.web.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 음식 추천 응답 Dto
 */

public class RecommendResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendFoodResponseDto{
		List<RecommendFoodDetailDto> food_list;
		List<String> allergen_list;
		List<String> preference_list;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendFoodDetailDto{
		private String name;
		private String image_url;
		// TODO 열량, 탄수화물, 당류 및 카테고리 추가
	}


}
