package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.demo.repository.IVogonprotokollRepository;
import com.demo.service.IVogonprotokollService;

public class VogonprotokollService implements IVogonprotokollService {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollService.class);

	// TODO: inject a IVogonprotokollRepository instance
	private IVogonprotokollRepository myVogonprotokollRepository;


	@Override
	public void schreibeSitzungsprotokoll() {
		log.debug("schreibeSitzungsprotokoll()");

		if (!this.myVogonprotokollRepository.istVogonSitzungsprotokollVorhanden()) {
			this.myVogonprotokollRepository.erzeugeVogonSitzungsprotokoll();
		} else {
			this.myVogonprotokollRepository.aktualisiereVogonSitzungsprotokoll();
		}
	}

}
