package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.demo.messaging.MessageSender;

@Aspect
@Component
@Order(Ordered.HIGHEST_PRECEDENCE - 10)
public class MetricsAspect {
	private static Logger log = LoggerFactory.getLogger(MetricsAspect.class);

	@Autowired private MessageSender messageSender;

	@Around("execution(public * (@com.demo.aop.Monitored com.demo..*).*(..))")
	public Object metrics(ProceedingJoinPoint point) throws Throwable {

		StopWatch timer = new StopWatch("Metrics Watch");
		final Object value;
		try {
			timer.start(point.toShortString());
			value = point.proceed();
			return value;
		} finally {
			timer.stop();
			log.info(timer.prettyPrint());

			messageSender.send(timer.shortSummary());

		}
	}
}
