package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StopWatch;

import com.demo.messaging.MessageSender;

@Aspect
public class MetricsAspect {
	private static Logger log = LoggerFactory.getLogger(MetricsAspect.class);

	
	@Autowired
	private MessageSender messageSender;
	
	
	@Around("com.demo.aop.MetricsPointcuts.serviceCalls()")
	public Object metrics(ProceedingJoinPoint point)  throws Throwable {
	
		StopWatch timer = new StopWatch("Metrics Watch");
		final Object value;
		try {
			timer.start(point.toShortString());
			value = point.proceed();			
		    return value;
		}
		finally {
			timer.stop();
			log.info(timer.prettyPrint());
			
			// TODO: 4. Send metrics message
		}
		
	}
	
	// Setter for construction from JUnit tests
	public void setMessageSender(MessageSender messageSender) {
		this.messageSender = messageSender;
	}
	
}
