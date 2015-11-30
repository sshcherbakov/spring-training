package com.demo.deepthought.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.deepthought.model.Antwort;
import com.demo.deepthought.model.Protokoll;
import com.demo.deepthought.service.api.IDeepThoughtService;
import com.demo.deepthought.service.api.IVogonprotokollService;

@RestController
public class AntwortController {
	
	@Autowired
	private IDeepThoughtService deepThoughtService;

	@Autowired
	private IVogonprotokollService vogonprotokollService;

	@Value("${spring.application.name:spring-training}")
	private String appName = "spring-training";
	

	@RequestMapping(value="/deep", method=RequestMethod.GET)
	public Antwort ermittleDieAntwort() {
		
		Antwort antwort = deepThoughtService.ermittleDieAntwort();
		vogonprotokollService.schreibeSitzungsprotokoll(antwort.getAntwort(), appName);
		return antwort;
	}

	
	@RequestMapping(value="/protokoll", method=RequestMethod.GET)
	public List<Protokoll> saveAntwort(@RequestParam("a") String anfragesteller) {
		
		return vogonprotokollService.ermittleProtokoll(anfragesteller); 
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