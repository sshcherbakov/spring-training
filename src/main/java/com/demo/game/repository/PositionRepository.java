package com.demo.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.game.model.Position;

//TODO: 2. Require transaction context presence
public interface PositionRepository extends JpaRepository<Position, Long> {

	Position findFirstByGameIdAndPlayer(Long gameId, String player);

	Iterable<Position> findByGameId(Long gameId);
	
}
