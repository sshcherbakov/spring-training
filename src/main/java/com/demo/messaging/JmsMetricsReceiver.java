package com.demo.messaging;

import java.io.File;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@ConditionalOnMissingBean({ RabbitTemplate.class })
@Profile("!cloud")
@Component
public class JmsMetricsReceiver {
	private static Logger log = LoggerFactory.getLogger(JmsMetricsReceiver.class);

	// TODO: 6. JMS Receiver implementation
    public void receiveMessage(String message) {
    	log.info("Received <{}>", message);
    }

    
    @PreDestroy
    public void cleanup() {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
    
}