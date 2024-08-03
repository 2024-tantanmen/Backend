package com.tantanmen.carbofootprint.domain.recommend.web.dto;

import java.util.List;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 음식 추천 응답 Dto
 */
@Schema(title = "RECOMMEND_RES_01", description = "음식 추천 응답 DTO")
public class RecommendResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendFoodResponseDto {
		@Schema(description = "저장된 사용자의 음식 추천 고유 번호, 없을 경우 null", example = "3")
		private Long member_recommend_id;
		@Schema(description = "추천 음식 목록")
		private List<RecommendFoodDetailDto> food_list;
		@Schema(description = "사용자가 입력한 알레르기 목록", example = "[\"난류\", \"고등어\"]")
		private List<String> allergen_list;
		@Schema(description = "사용자가 입력한 식습관 목록", example = "[\"아재_입맛\", \"다이어트\"]")
		private List<String> preference_list;
	}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class RecommendFoodDetailDto {
		@Schema(description = "음식 이름", example = "삼겹살")
		private String name;
		@Schema(description = "음식 이미지 URL 주소", example = "https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/foods/images/%EC%82%BC%EA%B2%B9%EC%82%B4.jpg")
		private String image_url;
		@Schema(description = "열량", example = "660")
		private Integer calorie;
		@Schema(description = "탄수화물", example = "0")
		private Integer carbohydrate;
		@Schema(description = "당류", example = "0")
		private Integer saccharide;
	}

}
