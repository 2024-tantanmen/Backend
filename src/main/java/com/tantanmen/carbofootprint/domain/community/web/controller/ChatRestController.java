package com.tantanmen.carbofootprint.domain.community.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tantanmen.carbofootprint.domain.community.convertor.ChatConvertor;
import com.tantanmen.carbofootprint.domain.community.entity.ChatRoom;
import com.tantanmen.carbofootprint.domain.community.entity.mapping.ChatMessage;
import com.tantanmen.carbofootprint.domain.community.service.ChatCommandService;
import com.tantanmen.carbofootprint.domain.community.service.ChatQueryService;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatRequestDto;
import com.tantanmen.carbofootprint.domain.community.web.dto.ChatResponseDto;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 채팅방 목록, 채팅 내역 등 Rest 요청 컨트롤러
 * 채팅(WebSocket)의 경우 ChatController 클래스에 작성
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/chat")
public class ChatRestController {
	private final ChatQueryService chatQueryService;
	private final ChatCommandService chatCommandService;

	/**
	 * 모든 채팅방 목록 조회
	 */
	@GetMapping("/rooms")
	public ApiResponse<List<ChatResponseDto.ChatRoomPreviewResponseDto>> getAllChatRooms() {
		List<ChatResponseDto.ChatRoomPreviewResponseDto> result = chatQueryService.getAllChatRooms()
			.stream()
			.map(ChatConvertor::toChatRoomPreviewResponseDto)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(result);
	}

	/**
	 * 채팅 방 생성
	 */
	@PostMapping("/room")
	public ApiResponse<ChatResponseDto.EnterChatRoomResponseDto> createRoom(@Valid @RequestBody
	ChatRequestDto.CreateChatRoomRequestDto request) {
		ChatRoom room = chatCommandService.createRoom(request);
		ChatResponseDto.EnterChatRoomResponseDto result = ChatResponseDto.EnterChatRoomResponseDto.builder()
			.chat_room_id(room.getId())
			.build();

		// 채팅 방 연결
		// TODO 사용자 유저 적용
		Long memberId = 1L;
		chatCommandService.enterChatRoom(room.getId(), memberId);
		return ApiResponse.onSuccess(result);
	}

	/**
	 * 이전 채팅 내역
	 */
	@GetMapping("/rooms/{roomId}/messages")
	public ApiResponse<List<ChatResponseDto.ChatMessageResponseDto>> getRoomChatMessages(
		@PathVariable(name = "roomId") Long roomId) {
		Long memberId = 1L;

		// TODO 임시유저 => JWT 로그인 유저로 변경하기
		List<ChatMessage> chatMessageList = chatQueryService.getRoomChatMessages(roomId, memberId);
		List<ChatResponseDto.ChatMessageResponseDto> result = ChatConvertor.toChatMessageResponseDtoList(
			chatMessageList, memberId);
		return ApiResponse.onSuccess(result);
	}
}
