package com.demo.messaging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({ "cloud", "rabbit" })
@Component
public class AmqpMetricsReceiver {
	private static Logger log = LoggerFactory.getLogger(AmqpMetricsReceiver.class);

	
    @RabbitListener(exclusive=true, bindings={
    @QueueBinding(
	    		exchange=@Exchange("metrics"),
	    		value=@Queue(exclusive="true"))
    })
    public void receiveMessage(String message) {
    	log.debug("Received <{}>", message);
    }
    
}