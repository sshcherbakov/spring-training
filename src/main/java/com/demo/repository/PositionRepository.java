package com.demo.repository;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Position;

public interface PositionRepository extends CrudRepository<Position, Long> {

	Position findFirstByGameIdAndPlayer(Long gameId, String player);

	Iterable<Position> findByGameId(Long gameId);
	
}
