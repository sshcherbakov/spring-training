package com.demo;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// TODO: Add Spring JUnit runner
// TODO: reference configurations for the test
// TODO: list SQL files to be loaded
public class ApplicationTests {
	private static Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	
	// TODO: Copy the DatabaseConfig configuration to the nested class here 
	// and override the database contents for the test  
	

	// TODO autowire DeepThoughtController into the unit test
	@Test
	public void contextLoads() {
		log.info("TEST");
		
		// TODO: compare DeepThoughtController answer with "Test"
	}

}
