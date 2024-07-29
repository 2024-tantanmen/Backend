package com.tantanmen.carbofootprint.domain.community.service;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;
import org.springframework.web.socket.messaging.SessionSubscribeEvent;

import com.tantanmen.carbofootprint.global.socket.SocketSessionRegistry;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Websocket Event Listener
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class ChatMessageListener {
	private final SocketSessionRegistry socketSessionRegistry;
	private final ChatCommandService chatCommandService;

	/**
	 * 토픽 구독 시 => 세션 데이터에 연결된 chatRoom ID 저장
	 */
	@EventListener
	public void handleWebSocketSubscribeListener(SessionSubscribeEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
		Long chatRoomId = Long.valueOf(headerAccessor.getDestination().substring(7));
		String loginId = (String) headerAccessor.getSessionAttributes().get("loginId");

		log.info("destination = {}", chatRoomId);

		if (chatRoomId != null) {
			socketSessionRegistry.addSubscription(loginId, chatRoomId); // 구독중인 토픽 추가
		}
	}

	/**
	 * 소켓 연결이 끊어진 경우
	 */
	@EventListener
	public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
		StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());

		String loginId = (String) headerAccessor.getSessionAttributes().get("loginId");
		if (loginId != null) {
			log.info("User Disconnected : " + loginId);

			onDisconnect(loginId);
		}
	}

	/**
	 * 사용자가 마지막으로 읽은 데이터 저장
	 */
	private void onDisconnect(String loginId) {
		for (Long chatRoomId : socketSessionRegistry.getSubscriptions(loginId)) {
			// 마지막 채팅 번호로 변경 후 저장
			chatCommandService.onWebSocketDisconnect(loginId, chatRoomId);
		}
	}
}
