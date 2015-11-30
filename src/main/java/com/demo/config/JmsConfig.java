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

// TODO: 1. Add JMS dependencies to the pom.xml
// TODO: 2. Enable JMS processing for the @JmsListener endpoint to be recognized and for 
// 		the Spring Boot to start the embedded ActiveMQ
@Configuration
public class JmsConfig {

	public final static String METRICS_DESTINATION = "metrics-destination"; 

// TODO: 5. Optional JMS connectionFactory customization 
//		(a connection factory is provided by Spring Boot by default)
//@Bean
//JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
//	SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//	factory.setConnectionFactory(connectionFactory);
//	return factory;
//}


	@Bean
	public MessageSender jmsMessageSender(JmsTemplate jmsTemplate) {
		return s -> { 
			// TODO: 4. Send metrics message
		};
	}

}
