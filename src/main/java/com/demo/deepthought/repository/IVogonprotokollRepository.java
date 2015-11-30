package com.demo.deepthought.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.demo.deepthought.model.Protokoll;

public interface IVogonprotokollRepository extends CrudRepository<Protokoll, Long> {

	List<Protokoll> findByAnfragesteller(String anfragesteller);
	
}