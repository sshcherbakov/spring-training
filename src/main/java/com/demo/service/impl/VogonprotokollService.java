package com.demo.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.repository.IVogonprotokollRepository;
import com.demo.service.IVogonprotokollService;

//TODO: Add @Service
public class VogonprotokollService implements IVogonprotokollService {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollService.class);

	@Autowired
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
