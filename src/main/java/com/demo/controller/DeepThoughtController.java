package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;

import com.demo.model.Antwort;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;

@Profile("!debug")
@Controller
public class DeepThoughtController implements IDeepThoughtController {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtController.class);
	
	private IDeepThoughtService deepThoughtService;

	private IVogonprotokollService vogonprotokollService;

	@Autowired
	public DeepThoughtController(
			IDeepThoughtService deepThoughtService,
			IVogonprotokollService vogonprotokollService) {
		
		this.deepThoughtService = deepThoughtService;
		this.vogonprotokollService = vogonprotokollService;
	}
	
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