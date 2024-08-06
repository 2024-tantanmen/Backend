package com.tantanmen.carbofootprint.domain.recommend.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.enums.AllergenType;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 음식 추천 요청 Dto
 */

@Schema(title = "RECOMMEND_REQ_01", description = "음식 추천 요청 DTO")
public class RecommendRequestDto {
	@Setter
	@Getter
	@ToString
	public static class RecommendFoodRequestDto {
		@Schema(description = "알레르기 목록", example = "[\"난류\", \"고등어\"]")
		private List<AllergenType> allergen_list;
		@Schema(description = "식습관 목록", example = "[\"아재_입맛\", \"다이어트\"]")
		private List<PreferenceType> preference_list;
	}

}
