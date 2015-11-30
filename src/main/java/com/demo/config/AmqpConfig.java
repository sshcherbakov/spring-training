package com.demo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demo.messaging.MessageSender;

@Profile({ "cloud", "rabbit" })
@EnableRabbit
@Configuration
public class AmqpConfig {
	private static Logger log = LoggerFactory.getLogger(AmqpConfig.class);

	@Value("${rabbit.exchangeName:metricsExchange}")
	private String rabbitExchangeName = "metricsExchange";
	
	@Bean
	public MessageSender amqpMessageSender(RabbitTemplate rabbitTemplate) {
		return s -> {
			log.debug("Sending {} to exchange {}", s, rabbitExchangeName);
			// TODO: 7. Alternative AMQP MessageSender
		};
	}
	
}
