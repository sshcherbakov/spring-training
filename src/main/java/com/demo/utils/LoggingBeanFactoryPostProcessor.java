package com.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;

public class LoggingBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	private static Logger log = LoggerFactory.getLogger(LoggingBeanFactoryPostProcessor.class);

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Analyze the BeanFactoryPostProcessor at runtime
		
    	BeanDefinitionRegistry beanRegistry = (BeanDefinitionRegistry) beanFactory;

		log.info("BeanDefinitions found in BeanDefinitionRegistry");
    	for(String beanDefinitionName : beanRegistry.getBeanDefinitionNames()) {
    		
    		BeanDefinition beanDefinition = beanRegistry.getBeanDefinition(beanDefinitionName);
    		if(beanDefinition.getBeanClassName() != null 
    				&& beanDefinition.getBeanClassName().startsWith("com.demo")) {
    			log.info("{}", beanDefinition);	
    		}
    		
    	}
		
	}

}