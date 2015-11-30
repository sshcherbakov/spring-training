package com.demo.deepthought.repository;

import org.springframework.data.repository.Repository;

import com.demo.deepthought.model.Antwort;

//TODO: 2. Require transaction context presence
public interface IDeepThoughtRepository extends Repository<Antwort, Long> {

	Antwort findOne(Long id);

	Antwort findByAntwort(String antwort);
	
}
