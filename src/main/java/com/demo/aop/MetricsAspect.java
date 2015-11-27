package com.demo.aop;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.util.StopWatch;

import com.demo.messaging.JmsMetricsReceiver;

@Aspect
public class MetricsAspect {
	private static Logger log = LoggerFactory.getLogger(MetricsAspect.class);

	
	@Autowired
	private JmsTemplate jmsTemplate;
	
	
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
			log.trace(timer.prettyPrint());
			
			jmsTemplate.send(JmsMetricsReceiver.METRICS_DESTINATION,
					new MessageCreator() {
						@Override
						public Message createMessage(Session session) throws JMSException {
							return session.createTextMessage(timer.shortSummary());
						}
					});

		}
		
	}
	
	// Setter for construction from JUnit tests
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}
	
}
