package com.tantanmen.carbofootprint.domain.mypage.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MyPageResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyPageRecommendResponseDto{
		private String date;
		private List<String> allergen_list;
		private List<String> preference_list;
		private List<RecommendResponseDto.RecommendFoodDetailDto> recommend_food_list;
	}
}
