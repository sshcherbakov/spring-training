package com.demo.deepthought.service.internal;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.demo.aop.Monitored;
import com.demo.deepthought.model.Protokoll;
import com.demo.deepthought.repository.IVogonprotokollRepository;
import com.demo.deepthought.service.api.IVogonprotokollService;

@Service
public class VogonprotokollService implements IVogonprotokollService {
	private static Logger log = LoggerFactory.getLogger(VogonprotokollService.class);

	// TODO: D4. Inject IVogonprotokollRepository
	private IVogonprotokollRepository myVogonprotokollRepository;


	@Monitored
	@Override
	public void schreibeSitzungsprotokoll(String antwort, String anfragesteller) {
		log.debug("schreibeSitzungsprotokoll()");

		Protokoll protokoll = new Protokoll();
		protokoll.setAntwort(antwort);
		protokoll.setAnfragesteller(anfragesteller);
		protokoll.setTimestamp(new Date());

		// TODO: D5. Save Protokoll to the database
	}


	@Override
	public List<Protokoll> ermittleProtokoll(String anfragesteller) {
		
		// TODO: D6. Find Protokoll by anfragesteller field
		return Collections.emptyList();
	}

}