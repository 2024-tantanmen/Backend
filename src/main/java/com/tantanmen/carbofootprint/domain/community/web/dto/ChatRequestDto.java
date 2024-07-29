package com.tantanmen.carbofootprint.domain.community.web.dto;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 커뮤니티 관련 요청 DTO
 */
public class ChatRequestDto {

	/**
	 * 채팅방 생성 요청 DTO
	 */
	@Getter
	@Setter
	@ToString
	public static class CreateChatRoomRequestDto {
		@NotBlank(message = "방 이름을 입력해주세요.")
		@Length(max = 10, message = "방 이름은 최대 10글자 입니다.")
		private String name; // 채팅방 이름

		@NotNull(message = "인원 수를 입력해주세요.")
		@Max(value = 40, message = "인원 수는 최대 40명 입니다.")
		private Integer max_capacity; // 채팅방 최대 인원 수
	}

	/**
	 * 웹소켓 채팅 전송 요청 DTO
	 */
	@Getter
	@Setter
	@ToString
	public static class SendChatRequestDto {
		private String content;
		private Long chat_room_id;
	}

	/**
	 * 채팅방 입장 웹 소켓 요청 DTO
	 */
	@Getter
	@Setter
	@ToString
	public static class EnterChatRoomRequestDto {
		private Long chat_room_id;
	}

}
