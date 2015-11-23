package com.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.demo.controller.DeepThoughtController;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.repository.IVogonprotokollRepository;
import com.demo.repository.impl.DeepThoughtRepository;
import com.demo.repository.impl.VogonprotokollRepository;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;
import com.demo.service.impl.DeepThoughtService;
import com.demo.service.impl.VogonprotokollService;

@Configuration
public class ApplicationConfig {

	@Bean
	public DeepThoughtController deepThoughtController() {
		return new DeepThoughtController();
	}
	
	@Bean
	public IDeepThoughtRepository deepThoughtRepository() {
		return new DeepThoughtRepository();
	}
	
	@Bean
	public IDeepThoughtService deepThoughtService(IDeepThoughtRepository deepThoughtRepository) {
		return new DeepThoughtService(deepThoughtRepository);
	}
	
	@Bean
	public IVogonprotokollRepository vogonprotokollRepository() {
		return new VogonprotokollRepository();
	}

	@Bean
	public IVogonprotokollService vogonprotokollService() {
		return new VogonprotokollService();
	}
	
}
