package com.demo.game.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.game.model.Position;

public interface PositionRepository extends JpaRepository<Position, Long> {

	Position findFirstByGameIdAndPlayer(Long gameId, String player);

	Iterable<Position> findByGameId(Long gameId);
	
}
