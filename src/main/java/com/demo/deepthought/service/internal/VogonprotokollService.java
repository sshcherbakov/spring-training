package com.demo.deepthought.service.internal;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.aop.Monitored;
import com.demo.deepthought.model.Protokoll;
import com.demo.deepthought.repository.IVogonprotokollRepository;
import com.demo.deepthought.service.api.IVogonprotokollService;

@Service
public class VogonprotokollService implements IVogonprotokollService {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollService.class);

	@Autowired
	private IVogonprotokollRepository myVogonprotokollRepository;


	@Monitored
	@Override
	public void schreibeSitzungsprotokoll(String antwort, String anfragesteller) {
		log.debug("schreibeSitzungsprotokoll()");

		Protokoll protokoll = new Protokoll();
		protokoll.setAntwort(antwort);
		protokoll.setAnfragesteller(anfragesteller);
		protokoll.setTimestamp(new Date());

		this.myVogonprotokollRepository.save(protokoll);
	}


	@Override
	public List<Protokoll> ermittleProtokoll(String anfragesteller) {
		return myVogonprotokollRepository.findByAnfragesteller(anfragesteller);
	}

}