package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.Antwort;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;


public class DeepThoughtController {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtController.class);

	// TODO: Autowire the instance
	private IDeepThoughtService deepThoughtService;

	// TODO: Autowire the instance
	private IVogonprotokollService vogonprotokollService;

	
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		final Antwort dieAntwort = this.deepThoughtService.ermittleDieAntwort();

		this.vogonprotokollService.schreibeSitzungsprotokoll();

		return dieAntwort;
	}
	
}