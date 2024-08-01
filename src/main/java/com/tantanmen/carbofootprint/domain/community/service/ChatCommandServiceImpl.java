package com.tantanmen.carbofootprint.domain.community.service;

import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.domain.member.exception.MemberNotExistException;
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
	public ChatRoom createRoom(ChatRequestDto.CreateChatRoomRequestDto request, Member member) {
		ChatRoom chatRoom = ChatConvertor.toChatRoom(request);
		chatRoomRepository.save(chatRoom);

		ChatMessage chatMessage = makeEnterChat(member.getLoginId(), chatRoom);
		saveEnterChat(chatMessage, member, chatRoom);

		return chatRoom;
	}

	/**
	 * 채팅방 연결 (입장)
	 * @return 채팅방 고유 번호 (ID)
	 */
	@Override
	public Long enterChatRoom(Long chatRoomId, String loginId) {
		Member member = memberRepository.findByLoginId(loginId).get();
		ChatRoom chatRoom = chatRoomRepository.findById(chatRoomId).orElseThrow(() -> new ChatRoomNotExistException());
		chatRoom.increaseCurrentCapacity();
		chatRoomRepository.save(chatRoom);

		ChatMessage chatMessage = makeEnterChat(member, chatRoom);
		saveEnterChat(chatMessage, member, chatRoom);
		return chatRoomId;
	}

	/**
	 * 입장 정보 채팅 저장
	 */
	public MemberChatRoom saveEnterChat(ChatMessage chatMessage, Member member, ChatRoom chatRoom){
		// 채팅방 연결 내역 추가
		MemberChatRoom memberChatRoom = MemberChatRoom.builder()
			.enterChatId(chatMessage.getId())
			.build();

		// 연관관계 매핑
		member.addMemberChatRoom(memberChatRoom);
		chatRoom.addMemberChatRoom(memberChatRoom);

		// 채팅방 입장 내역 데이터 저장
		return memberChatRoomRepository.save(memberChatRoom);
	}

	/**
	 * 입장 채팅 생성 및 저장
	 */
	public ChatMessage makeEnterChat(String loginId, ChatRoom chatRoom){
		Member member = memberRepository.findByLoginId(loginId).get();
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
		return chatMessage;
	}

	public ChatMessage makeEnterChat(Member member, ChatRoom chatRoom){
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
		return chatMessage;
	}

	/**
	 * 채팅 데이터 저장
	 */
	@Override
	@RabbitListener(queues = "chatQueue")
	public void saveMessage(ChatMessage chatMessage, Member member, ChatRoom chatRoom) {
		ChatMessage message = chatMessageRepository.save(chatMessage);
		member.addChatMessage(message);
		chatRoom.addChatMessage(message);
	}

	/**
	 * 메시지 웹 소켓 요청
	 * 요청 받은 메시지 -> MQ로 전달 후 저장
	 */

	@Override
	public Member sendMessage(ChatRequestDto.SendChatRequestDto request, String loginId){
		Long roomId = request.getChat_room_id();
		Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new MemberNotExistException());
		ChatRoom chatRoom = chatRoomRepository.findById(roomId).orElseThrow(() -> new ChatRoomNotExistException());

		ChatMessage chatMessage = ChatConvertor.toChatMessage(request);

		saveMessage(chatMessage, member, chatRoom);

		return member;
	}

	/**
	 * 웹 소켓 연결이 끊어진 경우
	 * 현재 채팅방의 가장 마지막 채팅 ID 저장
	 */
	@Override
	@RabbitListener(queues = "lastChatQueue")
	public void onWebSocketDisconnect(String loginId, Long roomId){
		Optional<MemberChatRoom> memberChatRoomOptional = memberChatRoomRepository.findByMemberLoginIdAndChatRoomId(
			loginId, roomId);
		if(memberChatRoomOptional.isEmpty()){
			log.error("memberChatRoom이 존재하지 않습니다. loginId = {}, roomId = {}", loginId, roomId);
			return;
		}

		MemberChatRoom memberChatRoom = memberChatRoomOptional.get();

		Optional<ChatMessage> chatMessageOptional = chatMessageRepository.findTopByChatRoomIdOrderByIdDesc(roomId);
		if(chatMessageOptional.isEmpty()){
			log.error("마지막 채팅이 없습니다. roomId = {}", roomId);
		}
		Long lastChatId = chatMessageOptional.get().getId();

		memberChatRoom.changeLastChatId(lastChatId);
		memberChatRoomRepository.save(memberChatRoom);
	}
}
