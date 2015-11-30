package com.demo.deepthought.repository;

import org.springframework.data.repository.Repository;

import com.demo.deepthought.model.Antwort;

// TODO: 1. Implement a Spring Data IDeepThoughtRepository repository
// 			- extend the Repository interface
//			- add findOne() form the CrudRepository interface
public interface IDeepThoughtRepository extends Repository<Antwort, Long> {

	Antwort findOne(Long id);

	Antwort findByAntwort(String antwort);
	
}
