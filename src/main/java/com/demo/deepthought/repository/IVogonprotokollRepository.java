package com.demo.deepthought.repository;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.demo.deepthought.model.Protokoll;

@Transactional(TxType.MANDATORY)
public interface IVogonprotokollRepository extends CrudRepository<Protokoll, Long> {

	List<Protokoll> findByAnfragesteller(String anfragesteller);
	
}