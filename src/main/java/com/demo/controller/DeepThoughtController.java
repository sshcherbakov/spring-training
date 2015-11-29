package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.demo.model.Antwort;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;

// TODO: 1. Add @Controller annotation
public class DeepThoughtController {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtController.class);
	
	@Autowired
	private IDeepThoughtService deepThoughtService;

	@Autowired
	private IVogonprotokollService vogonprotokollService;

	
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		final Antwort dieAntwort = this.deepThoughtService.ermittleDieAntwort();

		this.vogonprotokollService.schreibeSitzungsprotokoll();

		return dieAntwort;
	}
	
	
	@Scheduled(fixedDelayString="${service.delay:3000}")	
	public void repeat() {
		
    	Antwort antwort = ermittleDieAntwort();
    	log.info("Die Antwort ist \"{}\"", antwort);

	}
	
}