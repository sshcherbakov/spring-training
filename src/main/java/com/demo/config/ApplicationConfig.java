package com.demo.config;

import java.io.IOException;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.demo.controller.DeepThoughtController;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.repository.IVogonprotokollRepository;
import com.demo.repository.impl.DeepThoughtRepository;
import com.demo.repository.impl.VogonprotokollRepository;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IIdService;
import com.demo.service.IVogonprotokollService;
import com.demo.service.impl.DeepThoughtService;
import com.demo.service.impl.IdService;
import com.demo.service.impl.VogonprotokollService;

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
	
	
	// TODO: Remove all business bean definitions below

	@Bean
	public DeepThoughtController deepThoughtController() {
		return new DeepThoughtController();
	}
	
	@Bean
	public IDeepThoughtRepository deepThoughtRepository() {
		return new DeepThoughtRepository();
	}
	
	@Bean
	public IVogonprotokollRepository vogonprotokollRepository() {
		return new VogonprotokollRepository();
	}
	
	@Bean
	public IDeepThoughtService deepThoughtService(IDeepThoughtRepository deepThoughtRepository) {
		return new DeepThoughtService(deepThoughtRepository);
	}
	
	@Bean
	public IVogonprotokollService vogonprotokollService() {
		return new VogonprotokollService();
	}
	
	@Bean
	public IIdService idService() {
		return new IdService();
	}
	
}
