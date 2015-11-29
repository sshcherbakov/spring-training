package com.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.demo.config.ApplicationConfig;
import com.demo.controller.IDeepThoughtController;
import com.demo.model.Antwort;


public class SpringTrainingApplication {
	private static Logger log = LoggerFactory.getLogger(SpringTrainingApplication.class);

    public static void main(String[] args) {
    	
    	@SuppressWarnings("resource")
		ConfigurableApplicationContext context = 
    			new AnnotationConfigApplicationContext(
    					ApplicationConfig.class);
    	
    	IDeepThoughtController controller = context.getBean(IDeepThoughtController.class);
    	Antwort antwort = controller.ermittleDieAntwort();
    	log.info("Antwort: {}", antwort);
    	
    	context.registerShutdownHook();
    	
    }
    
}
