package com.demo.deepthought.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deepthought.model.Antwort;
import com.demo.deepthought.service.api.IDeepThoughtService;
import com.demo.deepthought.service.api.IVogonprotokollService;

@RestController
@RequestMapping("/deep")
public class AntwortController {
	
	@Autowired
	private IDeepThoughtService deepThoughtService;

	@Autowired
	private IVogonprotokollService vogonprotokollService;
	

	@RequestMapping(method=RequestMethod.GET)
	public Antwort ermittleDieAntwort() {
		
		return deepThoughtService.ermittleDieAntwort();
	}
	
	
}