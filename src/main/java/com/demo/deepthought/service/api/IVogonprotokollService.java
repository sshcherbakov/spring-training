package com.demo.deepthought.service.api;

import java.util.List;

import com.demo.deepthought.model.Protokoll;

public interface IVogonprotokollService {

	void schreibeSitzungsprotokoll(String antwort, String anfragesteller);

	List<Protokoll> ermittleProtokoll(String anfragesteller);

	
}