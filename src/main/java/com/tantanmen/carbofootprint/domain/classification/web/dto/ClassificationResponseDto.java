package com.tantanmen.carbofootprint.domain.classification.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
	public static class FoodClassificationResponseDto {
		@Schema(description = "음식 코드번호", example = "11015001")
		private String food_code;
		@Schema(description = "음식 이름", example = "잡채")
		private String name;
		@Schema(description = "음식 총량", example = "150")
		private Integer amount;
		@Schema(description = "총 열량", example = "198.82")
		private Double calorie;
		@Schema(description = "탄수화물 함량", example = "37.47")
		private Double carb; // carbohydrate
		@Schema(description = "단백질 함량", example = "2.59")
		private Double prot; // protein
		@Schema(description = "지방 함량", example = "4.7")
		private Double fat;
	}
}
