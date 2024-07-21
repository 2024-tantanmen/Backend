package com.tantanmen.carbofootprint.domain.recommend.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.tantanmen.carbofootprint.domain.recommend.entity.Food;
import com.tantanmen.carbofootprint.domain.recommend.enums.AllergenType;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;

public class RecommendConverter {

	// 추천 할 음식 리스트(Food 객체)를 응답 DTO로 변경
	public static RecommendResponseDto.RecommendFoodResponseDto toRecommendFoodResponseDto(List<Food> foodList, List<AllergenType> allergenTypeList, List<PreferenceType> preferenceTypeList){
		return RecommendResponseDto.RecommendFoodResponseDto.builder()
			.food_list(foodList.stream().map(food -> new RecommendResponseDto.RecommendFoodDetailDto(food.getFoodName(), food.getImageUrl())).collect(
				Collectors.toList()))
			.allergen_list(allergenTypeList.stream().map(Enum::name).collect(Collectors.toList()))
			.preference_list(preferenceTypeList.stream().map(Enum::name).collect(Collectors.toList()))
			.build();
	}
}
