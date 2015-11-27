package com.demo.deepthought.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.Repository;

import com.demo.deepthought.model.Antwort;

@Transactional(TxType.MANDATORY)
public interface IDeepThoughtRepository extends Repository<Antwort, Long> {

	Antwort findOne(Long id);

}
