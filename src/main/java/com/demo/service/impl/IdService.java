package com.demo.service.impl;

import java.util.List;
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

	private List<Integer> ids;

	private boolean isRunning = false;
	
	
	@Override
	public int getRandomId() {
		return ids.get(new Random().nextInt(ids.size()));
	}

	
	@Override
	public void start() {
		this.ids = jdbcTemplate.queryForList(
				"select id from antwort", Integer.class);
		log.info("start(): ids={}", ids);
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
