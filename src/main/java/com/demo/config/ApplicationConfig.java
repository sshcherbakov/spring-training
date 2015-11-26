package com.demo.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

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
	public IDeepThoughtRepository deepThoughtRepository(JdbcTemplate jdbcTemplate) {
		return new DeepThoughtRepository(jdbcTemplate);
	}
	
	@Bean
	public IDeepThoughtService deepThoughtService(IDeepThoughtRepository deepThoughtRepository) {
		return new DeepThoughtService(deepThoughtRepository);
	}
	
	@Bean
	public IVogonprotokollRepository vogonprotokollRepository() {
		return new VogonprotokollRepository();
	}

	// TODO Try double vogonprotokollRepository beans error and autowire by bean name 
	// TODO Demo @Primary and @Qulifier annotation
	
	@Bean
	public IVogonprotokollService vogonprotokollService() {
		return new VogonprotokollService();
	}
	
	
	// TODO Database configuration to separate 
	
	@Bean
	public DataSource dataSource() {
		return new EmbeddedDatabaseBuilder()
			.setType(EmbeddedDatabaseType.H2)
			.addScript("schema.sql")
			.addScript("data.sql")
			.build();
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}

	
}
