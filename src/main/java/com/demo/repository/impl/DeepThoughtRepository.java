package com.demo.repository.impl;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;


public class DeepThoughtRepository implements IDeepThoughtRepository {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtRepository.class);


	@Value("${spring.application.name}")
	private String applicationName;
	
	@Value("${spring.application.index}")
	private String applicationIndex;
	
	
	@Value("${max.id:4}")
	private int maxId = 4;

	@Autowired
	private JdbcTemplate jdbcTemplate;
		

	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		 return jdbcTemplate.queryForObject(
			"select val from ANTWORT where id = " + getRandomId(), 
			(rowMapper, rowNum) -> {
				
				Antwort antwort = new Antwort(rowMapper.getString("val"));
				antwort.setAnfragesteller(String.format("%s:%s", applicationName, applicationIndex));
				
				return antwort;
			}
		);

	}

	int getRandomId() {
		return new Random().nextInt(maxId) + 1;
	}

}
