package com.demo.repository.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.demo.repository.IVogonprotokollRepository;

public class VogonprotokollRepository implements IVogonprotokollRepository {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollRepository.class);


	@Override
	public boolean erzeugeVogonSitzungsprotokoll() {
		log.debug("erzeugeVogonSitzungsprotokoll()");
		return true;
	}

	@Override
	public boolean istVogonSitzungsprotokollVorhanden() {
		log.debug("istVogonSitzungsprotokollVorhanden()");
		return true;
	}

	@Override
	public boolean aktualisiereVogonSitzungsprotokoll() {
		log.debug("aktualisiereVogonSitzungsprotokoll()");
		return true;
	}

	@Override
	public boolean entferneVogonSitzungsprotokolle() {
		log.debug("entferneVogonSitzungsprotokolle()");
		return true;
	}
	
}
