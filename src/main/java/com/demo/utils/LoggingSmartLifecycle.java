package com.demo.utils;

import java.util.Arrays;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.SmartLifecycle;
import org.springframework.core.env.Environment;

public class LoggingSmartLifecycle
	implements InitializingBean, DisposableBean, SmartLifecycle, EnvironmentAware {

	private static Logger log = LoggerFactory.getLogger(LoggingSmartLifecycle.class);

	public LoggingSmartLifecycle() {
		log.debug("constructor()");
	}

	
	@PostConstruct
	private void init() {
		log.debug("@PostConstruct init()");
	}

	@PreDestroy
	private void clean() {
		log.debug("@PreDestroy clean()");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("InitialializingBean.afterPropertiesSet()");
	}

	@Override
	public void destroy() throws Exception {
		log.debug("DisposableBean.destroy()");
	}

	private boolean isRunning = false;
	
	@Override
	public void start() {
		log.debug("SmartLifecycle.start()");
		isRunning = true;
	}

	@Override
	public void stop() {
		log.debug("SmartLifecycle.stop()");		
		isRunning = false;
	}

	@Override
	public boolean isRunning() {
		log.debug("SmartLifecycle.isRunning() => {}", isRunning);
		return isRunning;
	}

	@Override
	public int getPhase() {
		log.debug("SmartLifecycle.getPhase() => 0");
		return 0;
	}

	@Override
	public boolean isAutoStartup() {
		log.debug("SmartLifecycle.isAutoStartup() => true");
		return true;
	}

	@Override
	public void stop(Runnable callback) {
		log.debug("SmartLifecycle.stop()");
		callback.run();
	}

	@Override
	public void setEnvironment(Environment environment) {
		log.debug("Active profiles: {}", Arrays.asList(environment.getActiveProfiles()));
	}

}