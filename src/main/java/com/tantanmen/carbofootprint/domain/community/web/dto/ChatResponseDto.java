package com.tantanmen.carbofootprint.domain.community.web.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 커뮤니티 관련 응답 DTO
 */
public class ChatResponseDto {
	/**
	 * 채팅방 목록 셀 데이터
	 */
	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	public static class ChatRoomPreviewResponseDto {
		private String room_name; // 채팅방 이름
		private Integer room_max_capacity; // 채팅방 최대 인원 수
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
		private String type; // 채팅 종류 me you in out
		private String date; // 발신 시각
		private String chat; // 채팅 내용
		private String sender_username; // 발신자 닉네임
	}
}
