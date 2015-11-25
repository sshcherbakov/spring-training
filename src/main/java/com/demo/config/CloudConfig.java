package com.demo.config;

import java.io.IOException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.config.java.AbstractCloudConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

@Profile("cloud")
@Configuration
public class CloudConfig extends AbstractCloudConfig {

	
	@Value("${db.initEnabled:false}")
	private boolean dbInitEnabled;
	
	
	@Bean
	public DataSource dataSource() {
		return connectionFactory().dataSource();
	}
	
	@Bean
	public DataSourceInitializer dataSourceInitializer(DataSource dataSource) throws IOException {
		DataSourceInitializer dataSourceInitializer 
			= new DataSourceInitializer();
		
		PathMatchingResourcePatternResolver resourceResolver = new PathMatchingResourcePatternResolver();
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(
				resourceResolver.getResource("classpath:db/sql/create-db.sql"),
				resourceResolver.getResource("classpath:db/sql/init-db.sql")
		);
		dataSourceInitializer.setDataSource(dataSource);
		dataSourceInitializer.setDatabasePopulator(databasePopulator);
		dataSourceInitializer.setEnabled(dbInitEnabled);
		
		return dataSourceInitializer;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
}
