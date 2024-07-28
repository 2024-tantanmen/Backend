package com.tantanmen.carbofootprint.domain.community.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;

public interface ChatQueryService {
	List<ChatRoom> getAllChatRooms();

	List<ChatMessage> getRoomChatMessages(Long memberId);
}
