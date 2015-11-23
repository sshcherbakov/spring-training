package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.config.ApplicationConfig;
import com.demo.controller.DeepThoughtController;

import com.demo.model.Antwort;


public class SpringTrainingApplication {
	private static Logger log = LoggerFactory.getLogger(SpringTrainingApplication.class);

    public static void main(String[] args) {
    	
    	try (ConfigurableApplicationContext context = 
    			new AnnotationConfigApplicationContext(ApplicationConfig.class) ) {

    		DeepThoughtController service = context.getBean(DeepThoughtController.class);
	    	
	    	Antwort antwort = service.ermittleDieAntwort();
	        
	    	log.info("Die Antwort ist \"{}\"", antwort);
    	}
    	
    }
    
}
