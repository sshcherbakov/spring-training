package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.model.Antwort;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;


public class DeepThoughtController {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtController.class);

	// TODO: 2. Inject a IDeepThoughtService instance
	private IDeepThoughtService deepThoughtService;

	// TODO: 2. Inject a IVogonprotokollService instance
	private IVogonprotokollService vogonprotokollService;

	
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		final Antwort dieAntwort = this.deepThoughtService.ermittleDieAntwort();

		this.vogonprotokollService.schreibeSitzungsprotokoll();

		return dieAntwort;
	}
	
}