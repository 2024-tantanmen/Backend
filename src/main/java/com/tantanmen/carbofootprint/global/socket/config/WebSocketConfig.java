package com.tantanmen.carbofootprint.global.socket.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;
import com.tantanmen.carbofootprint.global.socket.interceptor.JwtHandshakeInterceptor;

import lombok.RequiredArgsConstructor;

/**
 * Web Socket config
 */
@Configuration
@RequiredArgsConstructor
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
	private final JwtTokenProvider jwtTokenProvider;

	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.enableSimpleBroker("/topic");
		config.setApplicationDestinationPrefixes("/app"); // STOMP의 경우 prefix가 /app
	}

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/ws")
			.setAllowedOriginPatterns("*")
			.addInterceptors(new JwtHandshakeInterceptor(jwtTokenProvider))
			.withSockJS(); // http://[URI]/ws로 웹소켓 연결, SockJS가 아닌 경우 ws://[URI]/ws
	}

}
