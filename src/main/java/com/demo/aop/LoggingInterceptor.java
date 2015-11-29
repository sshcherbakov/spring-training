package com.demo.aop;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

// TODO: 2. Interceptor is not an AspectJ aspect but acts similar when used in Spring proxies 
@Profile("debug")
@Component
public class LoggingInterceptor implements MethodBeforeAdvice {
	private static Logger log = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public void before(Method method, Object[] args, Object target) throws Throwable {
		log.debug("LOG ACCESS: method={}, args={}, target={}", 
				method, args, target);
	}
		
}
