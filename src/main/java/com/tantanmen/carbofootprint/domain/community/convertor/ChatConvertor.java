package com.tantanmen.carbofootprint.domain.community.convertor;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatResponseDto;

public class ChatConvertor {
	/**
	 * ChatRoom -> 채팅 방 목록 셀 데이터
	 */
	public static ChatResponseDto.ChatRoomPreviewResponseDto toChatRoomPreviewResponseDto(ChatRoom chatRoom) {
		return ChatResponseDto.ChatRoomPreviewResponseDto.builder()
			.room_name(chatRoom.getName())
			.room_max_capacity(chatRoom.getMaxCapacity())
			.room_current_capacity(chatRoom.getCurrentCapacity())
			.build();
	}

	/**
	 * 채팅방 생성 요청 DTO -> ChatRoom
	 */
	public static ChatRoom toChatRoom(ChatRequestDto.CreateChatRoomRequestDto request) {
		return ChatRoom.builder()
			.name(request.getName())
			.maxCapacity(request.getMax_capacity())
			.currentCapacity(0)
			.chatMessageList(new ArrayList<>())
			.build();
	}

	/**
	 * 채팅 내역 => 요청한 사용자에 따른 chat type 변경 후
	 * 응답 DTO로 변경
	 */

	public static List<ChatResponseDto.ChatMessageResponseDto> toChatMessageResponseDtoList(
		List<ChatMessage> chatMessageList,
		Long memberId) {
		return chatMessageList.stream()
			.map(chatMessage -> toChatMessageResponseDto(chatMessage, memberId))
			.collect(Collectors.toList());
	}

	/**
	 * 채팅 작성자에 따른 type 변경
	 * 만약 in, out 일 경우 type 변경
	 */
	private static ChatResponseDto.ChatMessageResponseDto toChatMessageResponseDto(ChatMessage chatMessage,
		Long memberId) {
		String chatType = "system";
		switch (chatMessage.getType()) {
			case IN: {
				chatType = "in";
				break;
			}
			case OUT: {
				chatType = "out";
				break;
			}
			case CHAT: {
				if (chatMessage.getSender().getId() == memberId) {
					chatType = "me";
				} else {
					chatType = "you";
				}
			}
		}

		return ChatResponseDto.ChatMessageResponseDto.builder()
			.type(chatType)
			.date(chatMessage.getCreatedAt().format(DateTimeFormatter.ofPattern("HH:mm")))
			.chat(chatMessage.getContent())
			.sender_username(chatMessage.getSender().getUsername())
			.build();
	}
}
