package com.demo.game.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.demo.game.model.Game;

public interface GameRepository extends CrudRepository<Game, Long> {

	@Query("select g FROM Game g left join fetch g.positions")
	Iterable<Game> findAll();
	
}
