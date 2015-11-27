package com.demo.deepthought.service.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.deepthought.model.Antwort;
import com.demo.deepthought.repository.IVogonprotokollRepository;
import com.demo.deepthought.service.api.IVogonprotokollService;

@Service
public class VogonprotokollService implements IVogonprotokollService {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollService.class);

	@Autowired
	private IVogonprotokollRepository myVogonprotokollRepository;


	@Override
	public void schreibeSitzungsprotokoll(Long id) {
		log.debug("schreibeSitzungsprotokoll()");

		Antwort antwort = this.myVogonprotokollRepository.findOne(id);
		if (antwort == null) {
			antwort = new Antwort();
		}

		antwort = this.myVogonprotokollRepository.save(antwort);
	}

}