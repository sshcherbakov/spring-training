package com.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Aspect
@Profile("debug")
@Component
public class MetricsPointcuts {
	
	// TODO: 4. Change to cover repositories only
	// Named pointcut for reuse (see MetricsAdvice)
	@Pointcut("execution(* (@org.springframework.stereotype.Service com.demo..*).*(..))")
	public void serviceCalls() {
	}
	
}
