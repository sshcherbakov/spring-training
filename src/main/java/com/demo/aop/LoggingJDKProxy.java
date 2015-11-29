package com.demo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: 1. JDK Proxy (non-Spring) 
public class LoggingJDKProxy implements InvocationHandler {
	private static Logger log = LoggerFactory.getLogger(LoggingJDKProxy.class);

	
	// The target instance
	private Object invocationTarget;

	
	public LoggingJDKProxy(Object invocationTarget) {
		this.invocationTarget = invocationTarget;
	}

	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

		// Start time
		long startTime = System.nanoTime();

		// Invoke the method on the target instance
		Object result = method.invoke(invocationTarget, args);

		// Print the execution time
		log.info("Executed method {} in {} nanoseconds",
				method.getName(),
				(System.nanoTime() - startTime));

		// Return the result to the caller
		return result;
	}
	  
}
