package com.demo.deepthought.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.deepthought.model.Antwort;

public interface IVogonprotokollRepository extends CrudRepository<Antwort, Long> {
	
}