package com.demo;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.config.ApplicationConfig;


public class SpringTrainingApplication {

    public static void main(String[] args) {
    	
    	@SuppressWarnings("resource")
		ConfigurableApplicationContext context = 
    			new AnnotationConfigApplicationContext(
    					ApplicationConfig.class);
    		
    	context.registerShutdownHook();
    	
    }
    
}
