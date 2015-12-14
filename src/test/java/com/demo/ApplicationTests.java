package com.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.demo.game.service.api.GameService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringTrainingApplication.class)
public class ApplicationTests {
	private static Logger log = LoggerFactory.getLogger(ApplicationTests.class);

	@Autowired GameService service;

	@Test
	public void contextLoads() {
		log.info("TEST");
	}
}
