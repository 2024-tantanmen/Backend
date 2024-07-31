package com.tantanmen.carbofootprint.domain.community.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 커뮤니티 관련 응답 DTO
 */
@Schema(title = "COMMUNITY_RES_01", description = "커뮤니티 관련 응답 DTO")
public class ChatResponseDto {
	/**
	 * 채팅방 목록 셀 데이터
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChatRoomPreviewResponseDto {
		// TODO 채팅방 ID 전송 추가하기
		@Schema(description = "채팅방 이름", example = "탄수화몰 챌린지")
		private String room_name; // 채팅방 이름
		@Schema(description = "채팅방 최대 인원 제한", example = "20")
		private Integer room_max_capacity; // 채팅방 최대 인원 수
		@Schema(description = "채팅방 현재 인원 수", example = "6")
		private Integer room_current_capacity; // 채팅방 현재 인원 수
	}

	/**
	 * 채팅방 생성 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class EnterChatRoomResponseDto {
		@Schema(description = "생성된 채팅방의 고유 번호 값", example = "15")
		private Long chat_room_id; // 채팅방 고유 번호
	}

	/**
	 * 채팅 내역 응답 DTO
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChatMessageResponseDto {
		@Schema(description = "채팅 종류(you, in, out)", example = "you")
		private String type; // 채팅 종류 me you in out
		@Schema(description = "채팅 발신 시각 (HH:mm)", example = "15:33")
		private String date; // 발신 시각
		@Schema(description = "채팅 내용", example = "안녕하세요 !")
		private String chat; // 채팅 내용
		@Schema(description = "발신자의 Login ID", example = "junha123")
		private String sender_username; // 발신자 닉네임
	}
}
