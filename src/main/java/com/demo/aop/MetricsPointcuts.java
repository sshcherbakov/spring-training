package com.demo.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MetricsPointcuts {
	
	@Pointcut("execution(public * (@com.demo.aop.Monitored com.demo..*).*(..))")
	public void serviceCalls() {
	}
	
}
