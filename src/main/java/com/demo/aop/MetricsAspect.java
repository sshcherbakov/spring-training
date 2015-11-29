package com.demo.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

@Aspect
public class MetricsAspect {
	private static Logger log = LoggerFactory.getLogger(MetricsAspect.class);

		
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
		}
		
	}
	
}
