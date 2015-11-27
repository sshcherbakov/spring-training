package com.demo;

import org.aspectj.lang.Aspects;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

import com.demo.aop.MetricsAspect;


// TODO: Enable JMS here for the @JmsListener endpoint to be recognized and for 
// the Spring Boot to start the embedded Artemis 
@EnableJms
// TODO: Enable caching annotations processing
@EnableCaching
@SpringBootApplication
public class SpringTrainingApplication {

// TODO: Optional JMS connectionFactory customization 
// 		(a connection factory is provided by Spring Boot by default)
//	@Bean
//	JmsListenerContainerFactory<?> myJmsContainerFactory(ConnectionFactory connectionFactory) {
//		SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
//		factory.setConnectionFactory(connectionFactory);
//		return factory;
//	}
	
	
	// TODO: Autowire MetricsAdvice with JmsTemplate
	@Bean
	public MetricsAspect metricsAdvice(JmsTemplate jmsTemplate) {
		if( Aspects.hasAspect(MetricsAspect.class)) {
			return Aspects.aspectOf(MetricsAspect.class);
		}
		else { 	// for JUnit tests only
			MetricsAspect metricsAdvice = new MetricsAspect();
			metricsAdvice.setJmsTemplate(jmsTemplate);
			return metricsAdvice;
		}
	}

	
    public static void main(String[] args) {
    	SpringApplication.run(SpringTrainingApplication.class, args);
    }
    
}
