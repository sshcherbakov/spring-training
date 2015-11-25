package com.demo.repository;

import org.springframework.data.repository.Repository;

import com.demo.model.Antwort;

public interface IDeepThoughtRepository extends Repository<Antwort, Integer> {


	Antwort findOne(Integer id);

}
