package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.service.IDeepThoughtService;


public class DeepThoughtService implements IDeepThoughtService {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtService.class);

	private IDeepThoughtRepository deepThoughtRepository;

	// TODO: 3. Inject a IDeepThoughtService via constructor
	
	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");
		
		return this.deepThoughtRepository.ermittleDieAntwort();
		
	}

}
