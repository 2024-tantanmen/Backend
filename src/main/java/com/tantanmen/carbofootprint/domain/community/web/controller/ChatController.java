package com.tantanmen.carbofootprint.domain.community.web.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tantanmen.carbofootprint.domain.community.service.ChatCommandService;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatResponseDto;
import com.tantanmen.carbofootprint.domain.member.service.MemberService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ChatController {
	private final SimpMessagingTemplate messagingTemplate;
	private final ChatCommandService chatCommandService;

	@MessageMapping("/chat.sendMessage")
	public void sendMessage(@Payload ChatRequestDto.SendChatRequestDto request, SimpMessageHeaderAccessor headerAccessor) {

		log.info("chatRoomId = {}, content = {}", request.getChat_room_id(), request.getContent());
		String loginId = (String)headerAccessor.getSessionAttributes().get("loginId");
		chatCommandService.sendMessage(request, loginId);

		ChatResponseDto.ChatMessageResponseDto responseDto = ChatResponseDto.ChatMessageResponseDto.builder()
			.type("you")
			.chat(request.getContent())
			.date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
			.sender_username(loginId)
			.build();


		// 채팅방에 메시지 전송
		messagingTemplate.convertAndSend("/topic/" + request.getChat_room_id(), responseDto);
	}

	@MessageMapping("/chat.enterRoom")
	public void enterRoom(@Payload ChatRequestDto.EnterChatRoomRequestDto request, SimpMessageHeaderAccessor headerAccessor) {
		log.info("chat.enterRoom chatRoomId = {}", request.getChat_room_id());
		String loginId = (String)headerAccessor.getSessionAttributes().get("loginId");
		chatCommandService.enterChatRoom(request.getChat_room_id(), loginId);

		ChatResponseDto.ChatMessageResponseDto responseDto = ChatResponseDto.ChatMessageResponseDto.builder()
			.type("in")
			.chat(loginId + "님이 입장하셨습니다.")
			.date(LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm")))
			.sender_username(loginId)
			.build();


		// 채팅방에 메시지 전송
		messagingTemplate.convertAndSend("/topic/" + request.getChat_room_id(), responseDto);
	}
}
