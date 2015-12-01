package com.demo.game.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.game.model.Game;

@Transactional(TxType.MANDATORY)
public interface GameRepository extends CrudRepository<Game, Long> {

	@Query("select distinct g FROM Game g left join fetch g.positions")
	Iterable<Game> findAll();
	
}
