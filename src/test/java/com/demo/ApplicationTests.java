package com.demo;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.hsqldb.jdbcDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.ApplicationConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@ActiveProfiles("hsql")
public class ApplicationTests {
	private static Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	private DataSource dataSource;
	
	@Test
	public void contextLoads() throws SQLException {
		log.info("TEST");
		SimpleDriverDataSource ds = dataSource.unwrap(SimpleDriverDataSource.class);
		assertTrue(ds.getDriver() instanceof jdbcDriver);
	}

}
