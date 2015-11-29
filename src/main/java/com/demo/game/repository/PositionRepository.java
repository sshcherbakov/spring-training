package com.demo.game.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.game.model.Position;

@Transactional(TxType.MANDATORY)
public interface PositionRepository extends JpaRepository<Position, Long> {

	Position findFirstByGameIdAndPlayer(Long gameId, String player);

	Iterable<Position> findByGameId(Long gameId);
	
}
