package com.tantanmen.carbofootprint.domain.community.service;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;

public interface ChatCommandService {
	ChatRoom createRoom(ChatRequestDto.CreateChatRoomRequestDto request);

	Long enterChatRoom(Long chatRoomId, Long memberId);

	void saveMessage(ChatMessage chatMessage);
}
