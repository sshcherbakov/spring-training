package com.demo.config;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import com.demo.messaging.MessageSender;


@ConditionalOnMissingBean({ RabbitTemplate.class })
@Profile("!cloud")

@EnableJms
@Configuration
public class JmsConfig {

	public final static String METRICS_DESTINATION = "metrics-destination"; 

	
	@Bean
	public MessageSender jmsMessageSender(final JmsTemplate jmsTemplate) {
		return new MessageSender() {
			@Override
			public void send(final String message) {
				jmsTemplate.send(METRICS_DESTINATION,
						new MessageCreator() {
							@Override
							public Message createMessage(Session session) throws JMSException {
								return session.createTextMessage(message);
							}
						});
			}
		};
	}

}
