package com.demo.service.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.service.IIdService;

@Service
public class IdService implements IIdService, SmartLifecycle {
	private static Logger log = LoggerFactory.getLogger(IdService.class);

	@Autowired
	private JdbcTemplate jdbcTemplate;

	private int maxId = 0;

	private boolean isRunning = false;
	
	
	@Override
	public int getRandomId() {
		return new Random().nextInt(maxId) + 1;
	}

	
	@Override
	public void start() {
		this.maxId = jdbcTemplate.queryForObject(
				"select count(*) from ANTWORT", 
				(rowMapper, rowNum) -> {
					return rowMapper.getInt(1);
				}
			);
		log.debug("start(): maxId={}", maxId);
		isRunning = true;
	}

	@Override
	public void stop() {
		isRunning = false;
	}

	@Override
	public boolean isRunning() {
		return isRunning;
	}


	@Override
	public int getPhase() {
		return 0;
	}


	@Override
	public boolean isAutoStartup() {
		return true;
	}


	@Override
	public void stop(Runnable callback) {
		isRunning = false;
		callback.run();
	}

}
