package com.tantanmen.carbofootprint.global.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

	/**
	 * 채팅 저장 용 MQ
	 */
	@Bean
	public Queue chatQueue() {
		return new Queue("chatQueue");
	}

	/**
	 * 연결 종료 시 사용자가 확인한 마지막 채팅 저장 용 MQ
	 */
	@Bean
	public Queue lastChatQueue() {
		return new Queue("lastChatQueue");
	}
}
