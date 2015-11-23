package com.demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.demo.model.Antwort;
import com.demo.service.IDeepThoughtService;
import com.demo.service.IVogonprotokollService;

@Controller
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
	
}