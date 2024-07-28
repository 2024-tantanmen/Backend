package com.tantanmen.carbofootprint.domain.community.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.enums.ChatType;
import com.tantanmen.carbofootprint.domain.community.service.ChatCommandService;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatResponseDto;

@Controller
public class ChatController {
	private SimpMessagingTemplate messagingTemplate;
	private ChatCommandService chatCommandService;

	// @MessageMapping("/chat.sendMessage")
	// @SendTo("/topic/{roomId}")
	// public ChatResponseDto.ChatMessageResponseDto sendMessage(@Payload ChatRequestDto.SendChatRequestDto request) {
	//
	//
	//
	// 	chatCommandService.saveMessage(chatMessage);
	// 	ChatResponseDto.ChatMessageResponseDto.builder()
	// 		.
	// 	return chatMessage;
	// }
	//
	// @MessageMapping("/chat.addUser")
	// @SendTo("/topic/{roomId}")
	// public ChatMessage addUser(@Payload ChatMessage chatMessage) {
	// 	chatMessage.setContent(chatMessage.getSender() + " joined");
	// 	return chatMessage;
	// }
}
