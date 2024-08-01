package com.tantanmen.carbofootprint.domain.mypage.web.dto;

import java.util.List;

import com.tantanmen.carbofootprint.domain.recommend.web.dto.RecommendResponseDto;

import io.swagger.v3.oas.annotations.media.Schema;
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
}
