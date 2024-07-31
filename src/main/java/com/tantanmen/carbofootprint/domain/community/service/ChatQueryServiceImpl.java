package com.tantanmen.carbofootprint.domain.community.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.MemberChatRoom;
import com.tantanmen.carbofootprint.domain.community.repository.ChatMessageRepository;
import com.tantanmen.carbofootprint.domain.community.repository.ChatRoomRepository;
import com.tantanmen.carbofootprint.domain.community.repository.MemberChatRoomRepository;
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.global.enums.statuscode.ErrorStatus;
import com.tantanmen.carbofootprint.global.exception.GeneralException;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ChatQueryServiceImpl implements ChatQueryService {
	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final MemberChatRoomRepository memberChatRoomRepository;

	/**
	 * 모든 채팅 방 목록 데이터 조회
	 */
	@Override
	public List<ChatRoom> getAllChatRooms(Member member) {
		return chatRoomRepository.findAllByMemberNotExist(member.getId());
	}

	/**
	 * 채팅방의 채팅 메시지 조회
	 */
	@Override
	public List<ChatMessage> getRoomChatMessages(Long roomId, Long memberId) {
		MemberChatRoom memberChatRoom = memberChatRoomRepository.findByMemberIdAndChatRoomId(memberId, roomId)
			.orElseThrow(() -> new GeneralException(ErrorStatus._CHAT_ROOM_ENTRY_NOT_FOUND));
		List<ChatMessage> chatMessageList = chatMessageRepository.findAllByChatRoomIdAndIdGreaterThanEqualOrderByIdAsc(roomId,
			memberChatRoom.getEnterChatId());
		return chatMessageList;
	}

}
