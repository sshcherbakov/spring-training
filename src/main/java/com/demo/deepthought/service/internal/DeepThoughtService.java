package com.demo.deepthought.service.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.SmartLifecycle;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.demo.aop.Monitored;
import com.demo.deepthought.model.Antwort;
import com.demo.deepthought.repository.IDeepThoughtRepository;
import com.demo.deepthought.service.api.IDeepThoughtService;

@Service
//TODO: 1. Make Service transactional
public class DeepThoughtService implements IDeepThoughtService, SmartLifecycle {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtService.class);

	@Autowired
	private IDeepThoughtRepository deepThoughtRepository;

	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private List<Long> ids = new ArrayList<>();

	private boolean isRunning = false;

	
	
	@Monitored
	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");
		
		return this.deepThoughtRepository.findOne(getRandomId());
	}

		
	long getRandomId() {
		return ids.get( new Random().nextInt(ids.size()) );
	}

	
	@Override
	public void start() {
		this.ids = jdbcTemplate.queryForList("select id from t_antwort", Long.class);
		log.debug("start(): ids={}", ids);
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

