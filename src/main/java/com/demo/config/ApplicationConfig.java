package com.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@ComponentScan({ "com.demo" })
@PropertySource("classpath:application.properties")
@Configuration
public class ApplicationConfig {
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() 
			throws IOException {
		
		PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer 
			= new PropertySourcesPlaceholderConfigurer();
	
        propertySourcesPlaceholderConfigurer.setIgnoreUnresolvablePlaceholders(true);
        propertySourcesPlaceholderConfigurer.setLocations(
        		new ClassPathResource("application.properties"));
        		
		return propertySourcesPlaceholderConfigurer;
	}
		
}
