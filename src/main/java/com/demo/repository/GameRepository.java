package com.demo.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Game;

@Transactional(TxType.MANDATORY)
public interface GameRepository extends CrudRepository<Game, Long> {

}
