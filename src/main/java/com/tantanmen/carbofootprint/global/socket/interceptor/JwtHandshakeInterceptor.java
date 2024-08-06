package com.tantanmen.carbofootprint.global.socket.interceptor;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import com.tantanmen.carbofootprint.global.security.jwt.JwtTokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Handshake 단계에서 JWT 토큰을 통한 사용자 검증 후
 * socket session 에 사용자 정보 저장
 */
@Slf4j
@RequiredArgsConstructor
public class JwtHandshakeInterceptor implements HandshakeInterceptor {
	private final JwtTokenProvider jwtTokenProvider;

	/**
	 * handshake 이전에 JWT 검증
	 */
	@Override
	public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
		Map<String, Object> attributes) throws Exception {
		ServletServerHttpRequest servletRequest = (ServletServerHttpRequest) request;
		String token = servletRequest.getServletRequest().getParameter("token");
		log.info("beforeHandshake, token = {}", token);
		// JWT 토큰 추출
		if (token != null && jwtTokenProvider.validateToken(token)) {
			log.info("loginId = {}", jwtTokenProvider.getClaimsFromToken(token).getSubject());
			// 사용자 로그인 ID 저장
			attributes.put("loginId", jwtTokenProvider.getClaimsFromToken(token).getSubject());
			return true;
		}

		// 검증 실패 시
		response.setStatusCode(HttpStatus.UNAUTHORIZED);
		return false;
	}

	@Override
	public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response, WebSocketHandler wsHandler,
		Exception exception) {

	}

}
