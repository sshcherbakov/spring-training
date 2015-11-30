package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringTrainingApplication {
	
	
	// TODO: 3. Autowire MetricsAdvice with JmsTemplate
//	@Bean
//	public MetricsAspect metricsAdvice(MessageSender messageSender) {
//		if( Aspects.hasAspect(MetricsAspect.class)) {
//			return Aspects.aspectOf(MetricsAspect.class);
//		}
//		else { 	// for JUnit tests only
//			MetricsAspect metricsAdvice = new MetricsAspect();
//			metricsAdvice.setMessageSender(messageSender);
//			return metricsAdvice;
//		}
//	}

	
    public static void main(String[] args) {
    	SpringApplication.run(SpringTrainingApplication.class, args);
    }
    
}
