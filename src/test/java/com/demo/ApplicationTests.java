package com.demo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.config.ApplicationConfig;
import com.demo.config.DatabaseConfig;
import com.demo.controller.DeepThoughtController;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { ApplicationConfig.class, DatabaseConfig.class } )
public class ApplicationTests {
	private static Logger log = LoggerFactory.getLogger(ApplicationTests.class);
	
	@Autowired
	private DeepThoughtController controller;
	
	@Test
	public void contextLoads() {
		log.info("TEST");
		
		controller.ermittleDieAntwort();
	}

}
