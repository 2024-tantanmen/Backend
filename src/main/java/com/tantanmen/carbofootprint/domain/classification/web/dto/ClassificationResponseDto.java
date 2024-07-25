package com.tantanmen.carbofootprint.domain.classification.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ClassificationResponseDto {

	/**
	 * 음식 사진 인식 응답 Dto
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FoodClassificationResponseDto{
		private String food_code;
		private String name;
		private Integer amount;
		private Double calorie;
		private Double carb; // carbohydrate
		private Double prot; // protein
		private Double fat;
	}
}
