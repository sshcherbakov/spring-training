package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.demo.utils.LoggingBeanFactoryPostProcessor;
import com.demo.utils.LoggingBeanPostProcessor;
import com.demo.utils.LoggingSmartLifecycle;

@Profile("debug")
@Configuration
public class DebugConfig {
	
	@Bean
	public LoggingBeanFactoryPostProcessor loggingBeanFactoryPostProcessor() {
		return new LoggingBeanFactoryPostProcessor();
	}
	
	@Bean
	public LoggingBeanPostProcessor loggingBeanPostProcessor() {
		return new LoggingBeanPostProcessor();
	}
	
	@Bean
	public LoggingSmartLifecycle loggingSmartLifecycle() {
		return new LoggingSmartLifecycle();
	}
	
}