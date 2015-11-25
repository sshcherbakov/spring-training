package com.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class LoggingBeanPostProcessor implements BeanPostProcessor {
	private static Logger log = LoggerFactory.getLogger(LoggingBeanPostProcessor.class);

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

		log.info("postProcessBeforeInitialization bean {}", bean);		
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		
		log.info("postProcessAfterInitialization bean {}", bean);		
		return bean;
	}

}