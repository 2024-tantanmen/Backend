package com.tantanmen.carbofootprint.domain.community.service;

import java.util.List;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.mypage.web.dto.MyPageResponseDto;

public interface ChatQueryService {
	List<ChatRoom> getAllChatRoomsByMemberNotExist(Member member);

	List<ChatMessage> getRoomChatMessages(Long roomId, Long memberId);
	List<MyPageResponseDto.MyPageChatRoomResponseDto> getMyPageChatRoomList(Member member);
}
