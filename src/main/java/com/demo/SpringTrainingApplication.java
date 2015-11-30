package com.demo;

import org.aspectj.lang.Aspects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import com.demo.aop.MetricsAspect;
import com.demo.messaging.MessageSender;

@EnableCaching
@SpringBootApplication
public class SpringTrainingApplication {
	
	
	@Bean
	public MetricsAspect metricsAdvice(MessageSender messageSender) {
		if( Aspects.hasAspect(MetricsAspect.class)) {
			return Aspects.aspectOf(MetricsAspect.class);
		}
		else { 	// for JUnit tests only
			MetricsAspect metricsAdvice = new MetricsAspect();
			metricsAdvice.setMessageSender(messageSender);
			return metricsAdvice;
		}
	}

	
    public static void main(String[] args) {
    	SpringApplication.run(SpringTrainingApplication.class, args);
    }
    
}
