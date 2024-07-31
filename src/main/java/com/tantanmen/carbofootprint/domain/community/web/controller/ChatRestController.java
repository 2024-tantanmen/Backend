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
import com.tantanmen.carbofootprint.domain.member.entity.Member;
import com.tantanmen.carbofootprint.global.annotation.LoginMember;
import com.tantanmen.carbofootprint.global.response.ApiResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 채팅방 목록, 채팅 내역 등 Rest 요청 컨트롤러
 * 채팅(WebSocket)의 경우 ChatController 클래스에 작성
 */
@Tag(name = "커뮤니티 관련 Rest API", description = "커뮤니티(채팅) 관련 기능 API")
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
	@Operation(summary = "채팅방 입장 목록 조회 API", description = "사용자가 입장할 수 있는(현재 입장하지 않은) 모든 채팅방 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ChatResponseDto.ChatRoomPreviewResponseDto.class
					)
				)
			}
		)
	})
	@GetMapping("/rooms")
	public ApiResponse<List<ChatResponseDto.ChatRoomPreviewResponseDto>> getAllChatRooms(@Parameter(hidden = true) @LoginMember Member member) {
		List<ChatResponseDto.ChatRoomPreviewResponseDto> result = chatQueryService.getAllChatRooms(member)
			.stream()
			.map(ChatConvertor::toChatRoomPreviewResponseDto)
			.collect(Collectors.toList());

		return ApiResponse.onSuccess(result);
	}

	/**
	 * 채팅 방 생성
	 */
	@Operation(summary = "채팅방 생성 API", description = "채팅방 생성 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ChatResponseDto.EnterChatRoomResponseDto.class
					)
				)
			}
		)
	})
	@PostMapping("/room")
	public ApiResponse<ChatResponseDto.EnterChatRoomResponseDto> createRoom(@Valid @RequestBody
	ChatRequestDto.CreateChatRoomRequestDto request, @Parameter(hidden = true) @LoginMember Member member) {
		ChatRoom chatRoom = chatCommandService.createRoom(request, member);

		ChatResponseDto.EnterChatRoomResponseDto result = ChatResponseDto.EnterChatRoomResponseDto.builder()
			.chat_room_id(chatRoom.getId())
			.build();

		return ApiResponse.onSuccess(result);
	}

	/**
	 * 이전 채팅 내역
	 */

	@Operation(summary = "이전 채팅 내역 조회 API", description = "사용자가 채팅방에 입장 한 이후의 모든 채팅 내역 조회 API")
	@ApiResponses(value = {
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "COMMON200",
			description = "요청 성공",
			content = {
				@Content(
					schema = @Schema(
						implementation = ChatResponseDto.ChatMessageResponseDto.class
					)
				)
			}
		),
		@io.swagger.v3.oas.annotations.responses.ApiResponse(
			responseCode = "CHAT4001",
			description = "요청한 채팅방이 아직 사용자가 입장하지 않은 채팅방인 경우",
			content = {
				@Content(
					schema = @Schema(
						example = "입장한 내역이 없는 채팅방입니다."
					)
				)
			}
		)
	})
	@GetMapping("/rooms/{roomId}/messages")
	public ApiResponse<List<ChatResponseDto.ChatMessageResponseDto>> getRoomChatMessages(
		@PathVariable(name = "roomId") Long roomId, @Parameter(hidden = true) @LoginMember Member member) {
		List<ChatMessage> chatMessageList = chatQueryService.getRoomChatMessages(roomId, member.getId());
		List<ChatResponseDto.ChatMessageResponseDto> result = ChatConvertor.toChatMessageResponseDtoList(
			chatMessageList, member.getId());
		return ApiResponse.onSuccess(result);
	}
}
