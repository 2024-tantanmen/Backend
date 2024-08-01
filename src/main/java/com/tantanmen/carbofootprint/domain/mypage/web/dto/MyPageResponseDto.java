package com.tantanmen.carbofootprint.domain.mypage.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(title = "MYPAGE_RES_01", description = "마이페이지 관련 응답 DTO")
public class MyPageResponseDto {
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyPageRecommendResponseDto{
		@Schema(description = "업로드 날짜", example = "08.21.수요일")
		private String date;
		@Schema(description = "사용자가 입력한 알레르기 목록", example = "[\"난류\", \"고등어\"]")
		private List<String> allergen_list;
		@Schema(description = "사용자가 입력한 식습관 목록", example = "[\"아재_입맛\", \"다이어트\"]")
		private List<String> preference_list;
		@Schema(description = "추천 음식 목록")
		private List<RecommendResponseDto.RecommendFoodDetailDto> recommend_food_list;
	}

	/**
	 * 채팅방 목록 데이터
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyPageChatRoomResponseDto {
		@Schema(description = "채팅방 고유 번호", example = "1")
		private Long room_id;
		@Schema(description = "읽지 않은 채팅 수", example = "15")
		private long unchecked_message_count;
		@Schema(description = "채팅방 이름", example = "탄수화물 챌린지")
		private String room_name; // 채팅방 이름
		@Schema(description = "채팅방 최대 인원 제한", example = "20")
		private Integer room_max_capacity; // 채팅방 최대 인원 수
		@Schema(description = "채팅방 현재 인원 수", example = "6")
		private Integer room_current_capacity; // 채팅방 현재 인원 수
	}

	/**
	 * 음식 사진 인식 결과 데이터 응답 Dto
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class MyPageClassificationResponseDto {
		@Schema(description = "업로드 날짜", example = "08.21.수요일")
		private String date;
		@Schema(description = "사진 인식에 사용된 이미지의 URL", example = "https://carbofootprint-bucket.s3.ap-northeast-2.amazonaws.com/%2F%2Fclassification/imagesjunha341a56ff9a-0824-4bee-b5a1-4bbf30d13524")
		private String image_url;
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
		@Schema(description = "당류 함량", example = "2.99")
		private Double sugar;
	}
}
