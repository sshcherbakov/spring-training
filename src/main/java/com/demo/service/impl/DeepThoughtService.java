package com.demo.service.impl;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.service.IDeepThoughtService;

//TODO: Add @Service annotation
@Service
public class DeepThoughtService implements IDeepThoughtService, 
	InitializingBean, DisposableBean, SmartLifecycle, EnvironmentAware {
	
	private static Logger log = LoggerFactory.getLogger(DeepThoughtService.class);

	private final IDeepThoughtRepository deepThoughtRepository;

	@Autowired
	public DeepThoughtService(final IDeepThoughtRepository deepThoughtRepository) {
		log.debug("constructor()");
		this.deepThoughtRepository = deepThoughtRepository;
	}

	
	@PostConstruct
	private void init() {
		// TODO: @PostConstruct init() invocation
		log.debug("@PostConstruct init()");
	}

	@PreDestroy
	private void clean() {
		// TODO: @PreDestroy clean() invocation
		log.debug("@PreDestroy clean()");
	}

	
	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");
		
		return this.deepThoughtRepository.ermittleDieAntwort();
	
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO: Add InitialializingBean.afterPropertiesSet()
		log.debug("InitialializingBean.afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		// TODO: Add DisposableBean.destroy()
		log.debug("DisposableBean.destroy()");
	}

	private boolean isRunning = false;
	
	@Override
	public void start() {
		// TODO: Add SmartLifecycle.start()
		log.debug("SmartLifecycle.start()");
		isRunning = true;
	}

	@Override
	public void stop() {
		// TODO: Add SmartLifecycle.stop()
		log.debug("SmartLifecycle.stop()");		
		isRunning = false;
	}

	@Override
	public boolean isRunning() {
		// TODO: Add SmartLifecycle.isRunning()
		log.debug("SmartLifecycle.isRunning() => {}", isRunning);
		return isRunning;
	}

	@Override
	public int getPhase() {
		// TODO: Add SmartLifecycle.getPhase()
		log.debug("SmartLifecycle.getPhase() => 0");
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		// TODO: Add SmartLifecycle.isAutoStartup()
		log.debug("SmartLifecycle.isAutoStartup() => true");
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		// TODO: Add SmartLifecycle.stop()
		log.debug("SmartLifecycle.stop()");
		callback.run();
	}

	@Override
	public void setEnvironment(Environment environment) {
		log.debug("Active profiles: {}", Arrays.asList(environment.getActiveProfiles()));
	}
	
}
