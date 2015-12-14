package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class SpringTrainingApplication implements HealthIndicator {

	@Override
	public Health health() {
		return Health.up().withDetail("hello", "world").build();
	}

	public static void main(String[] args) {
		SpringApplication.run(SpringTrainingApplication.class, args);
	}

}
