package com.tantanmen.carbofootprint.domain.community.service;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.repository.MemberRepository;
import com.tantanmen.carbofootprint.domain.community.convertor.ChatConvertor;
import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.MemberChatRoom;
import com.tantanmen.carbofootprint.domain.community.enums.ChatType;
import com.tantanmen.carbofootprint.domain.community.exception.ChatRoomNotExistException;
import com.tantanmen.carbofootprint.domain.community.repository.ChatMessageRepository;
import com.tantanmen.carbofootprint.domain.community.repository.ChatRoomRepository;
import com.tantanmen.carbofootprint.domain.community.repository.MemberChatRoomRepository;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ChatCommandServiceImpl implements ChatCommandService {
	private final ChatRoomRepository chatRoomRepository;
	private final ChatMessageRepository chatMessageRepository;
	private final MemberChatRoomRepository memberChatRoomRepository;
	private final MemberRepository memberRepository;

	/**
	 * 채팅방 생성
	 */
	@Override
	public ChatRoom createRoom(ChatRequestDto.CreateChatRoomRequestDto request) {
		ChatRoom chatRoom = ChatConvertor.toChatRoom(request);
		return chatRoomRepository.save(chatRoom);
	}

	/**
	 * 채팅방 연결 (입장)
	 * @return 채팅방 고유 번호 (ID)
	 */
	@Override
	public Long enterChatRoom(Long chatRoomId, Long memberId) {
		// TODO controller에서 받아온 Member로 변경
		Member member = memberRepository.findById(memberId).get();

		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new ChatRoomNotExistException());

		// 입장 알림 채팅 추가
		ChatMessage chatMessage = ChatMessage.builder()
			.content("입장")
			.type(ChatType.IN)
			.build();

		// 연관관계 매핑
		member.addChatMessage(chatMessage);
		chatRoom.addChatMessage(chatMessage);

		// 채팅방 입장 채팅 메시지 데이터 저장
		chatMessageRepository.save(chatMessage);

		// 채팅방 연결 내역 추가
		MemberChatRoom memberChatRoom = MemberChatRoom.builder()
			.enterChatId(chatMessage.getId())
			.build();

		// 연관관계 매핑
		member.addMemberChatRoom(memberChatRoom);
		chatRoom.addMemberChatRoom(memberChatRoom);

		// 채팅방 입장 내역 데이터 저장
		memberChatRoomRepository.save(memberChatRoom);

		return chatRoomId;
	}

	/**
	 * 채팅 데이터 저장
	 */
	@Override
	@RabbitListener(queues = "chatQueue")
	public void saveMessage(ChatMessage chatMessage) {
		chatMessageRepository.save(chatMessage);
	}

	/**
	 * 메시지 웹 소켓 요청
	 * 요청 받은 메시지 -> MQ로 전달 후 저장
	 */

	public ChatMessage sendMessage(ChatRequestDto.SendChatRequestDto request){
		ChatMessage.builder()
			.content(request.getContext())
			.type(ChatType.CHAT)
	}
}
