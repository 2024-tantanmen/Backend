package com.tantanmen.carbofootprint.domain.community.service;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;
import com.tantanmen.carbofootprint.domain.member.entity.Member;

public interface ChatCommandService {
	ChatRoom createRoom(ChatRequestDto.CreateChatRoomRequestDto request, Member member);

	Long enterChatRoom(Long chatRoomId, String loginId);

	void saveMessage(ChatMessage chatMessage, Member member, ChatRoom chatRoom);
	Member sendMessage(ChatRequestDto.SendChatRequestDto request, String loginId);

	void onWebSocketDisconnect(String loginId, Long roomId);
}
