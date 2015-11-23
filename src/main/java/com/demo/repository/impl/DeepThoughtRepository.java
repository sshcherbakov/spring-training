package com.demo.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;


@Repository
public class DeepThoughtRepository implements IDeepThoughtRepository {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtRepository.class);


	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		return new Antwort("42");

	}
	
}
