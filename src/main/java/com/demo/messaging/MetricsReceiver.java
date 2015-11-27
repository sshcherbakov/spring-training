package com.demo.messaging;

import java.io.File;

import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.util.FileSystemUtils;

@Component
public class MetricsReceiver {
	private static Logger log = LoggerFactory.getLogger(MetricsReceiver.class);

	public final static String METRICS_DESTINATION = "metrics-destination"; 
	
    @JmsListener(destination = METRICS_DESTINATION)
    public void receiveMessage(String message) {
    	log.debug("Received <{}>", message);
    }

    
    @PreDestroy
    public void cleanup() {
        FileSystemUtils.deleteRecursively(new File("activemq-data"));
    }
    
}