package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: C1. Enable processing of caching annotations
// TODO: C10. Switch to Guava cache in the pom.xml
// @EnableCaching
// @EnableJpaRepositories 		<-- for a non-Spring Boot application
@SpringBootApplication
public class SpringTrainingApplication {
	
	
    public static void main(String[] args) {
    	SpringApplication.run(SpringTrainingApplication.class, args);
    }
    
}
