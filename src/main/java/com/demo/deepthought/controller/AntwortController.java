package com.demo.deepthought.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deepthought.model.Antwort;
import com.demo.deepthought.service.api.IDeepThoughtService;

@RestController
@RequestMapping("/deep")
public class AntwortController {
	
	@Autowired
	private IDeepThoughtService deepThoughtService;

//	@Autowired
//	private IVogonprotokollService vogonprotokollService;
	

	@RequestMapping(method=RequestMethod.GET)
	public Antwort ermittleDieAntwort() {
		
		return deepThoughtService.ermittleDieAntwort();
	}


	@ExceptionHandler()
	public void handleException(Exception ex, HttpServletResponse response) throws IOException {
		response.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), getStackString(ex));
	}

	private String getStackString(Exception e) {
		StringBuilder s = new StringBuilder(e.getClass().getCanonicalName()).append(e.getMessage());
		for (StackTraceElement ste : e.getStackTrace()) {
			s.append("  at ").append(ste.toString()).append("\n");
		}
		return s.toString();
	}

}