package com.demo.repository.impl;

import java.util.Random;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.demo.model.Antwort;
import com.demo.repository.IDeepThoughtRepository;


@Repository
public class DeepThoughtRepository implements IDeepThoughtRepository {
	private static Logger log = LoggerFactory.getLogger(DeepThoughtRepository.class);


	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	private int maxId = 0;
	
	
	@PostConstruct
	private void init() {
		
		this.maxId = jdbcTemplate.queryForObject(
			"select count(*) maxId from ANTWORT", 
			(rowMapper, rowNum) -> {
				return rowMapper.getInt(1);
			}
		);
	}

	@Override
	public Antwort ermittleDieAntwort() {
		log.debug("ermittleDieAntwort()");

		 return jdbcTemplate.queryForObject(
			"select val from ANTWORT where id = " + getRandomId(), 
			(rowMapper, rowNum) -> {
				return new Antwort(rowMapper.getString("val"));
			}
		);

	}

	int getRandomId() {
		return new Random().nextInt(maxId) + 1;
	}

}
