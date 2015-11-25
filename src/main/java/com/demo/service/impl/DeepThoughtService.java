package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IIdService;


@Service
public class DeepThoughtService implements IDeepThoughtService {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtService.class);

	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${spring.application.index}")
	private String applicationIndex;
	
	
	private final IDeepThoughtRepository 	deepThoughtRepository;
	private final IIdService 				idService;

	@Autowired
	public DeepThoughtService(
			final IDeepThoughtRepository deepThoughtRepository,
			final IIdService idService ) {
		
		this.deepThoughtRepository = deepThoughtRepository;
		this.idService = idService;
	}

	
	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");
		
		Antwort antwort = this.deepThoughtRepository.findOne(idService.getRandomId());
		antwort.setAnfragesteller(String.format("%s:%s", applicationName, applicationIndex));

		return antwort;
	
	}
	
}
