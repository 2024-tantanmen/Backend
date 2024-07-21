package com.tantanmen.carbofootprint.domain.recommend.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.enums.AllergenType;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 음식 추천 요청 Dto
 */
public class RecommendRequestDto {
	@Setter
	@Getter
	@ToString
	public static class RecommendFoodRequestDto{
		private List<AllergenType> allergen_list;
		private List<PreferenceType> preference_list;
	}

}
