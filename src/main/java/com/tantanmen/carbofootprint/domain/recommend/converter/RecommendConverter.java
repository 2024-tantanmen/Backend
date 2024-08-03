package com.tantanmen.carbofootprint.domain.recommend.converter;

import java.util.List;
import java.util.stream.Collectors;

import com.tantanmen.carbofootprint.domain.recommend.entity.FoodRecommend;
import com.tantanmen.carbofootprint.domain.recommend.enums.AllergenType;
import com.tantanmen.carbofootprint.domain.recommend.enums.PreferenceType;
import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;

public class RecommendConverter {

	// 추천 할 음식 리스트(Food 객체)를 응답 DTO로 변경
	public static RecommendResponseDto.RecommendFoodResponseDto toRecommendFoodResponseDto(List<FoodRecommend> foodRecommendList, List<AllergenType> allergenTypeList, List<PreferenceType> preferenceTypeList, Long memberFoodRecommendId){
		return RecommendResponseDto.RecommendFoodResponseDto.builder()
			.member_recommend_id(memberFoodRecommendId)
			.food_list(foodRecommendList.stream().map(food -> new RecommendResponseDto.RecommendFoodDetailDto(food.getFoodName(), food.getImageUrl(), food.getCalorie(), food.getCarbohydrate(), food.getSaccharide())).collect(
				Collectors.toList()))
			.allergen_list(allergenTypeList.stream().map(Enum::name).collect(Collectors.toList()))
			.preference_list(preferenceTypeList.stream().map(Enum::name).collect(Collectors.toList()))
			.build();
	}
}
