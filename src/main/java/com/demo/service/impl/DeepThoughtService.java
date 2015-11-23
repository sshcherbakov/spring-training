package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.service.IDeepThoughtService;


public class DeepThoughtService implements IDeepThoughtService {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtService.class);

	private final IDeepThoughtRepository deepThoughtRepository;

	@Autowired
	public DeepThoughtService(final IDeepThoughtRepository deepThoughtRepository) {
		this.deepThoughtRepository = deepThoughtRepository;
	}

	
	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");
		
		return this.deepThoughtRepository.ermittleDieAntwort();
		
	}
	
}
