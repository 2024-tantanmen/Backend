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
}
