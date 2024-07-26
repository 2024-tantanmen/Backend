package com.tantanmen.carbofootprint.domain.classification.convertor;

import com.tantanmen.carbofootprint.domain.classification.entity.Food;
import com.tantanmen.carbofootprint.domain.classification.web.dto.ClassificationResponseDto;

public class ClassificationConvertor {

	// Food Entity => dto convert
	public static ClassificationResponseDto.FoodClassificationResponseDto toFoodClassificationResponseDto(Food food) {
		return ClassificationResponseDto.FoodClassificationResponseDto.builder()
			.food_code(food.getCode())
			.name(food.getName())
			.amount(food.getAmount())
			.calorie(food.getKcal())
			.carb(food.getCarbohydrate())
			.prot(food.getProtein())
			.fat(food.getFat())
			.sugar(food.getSugar())
			.build();
	}

}
