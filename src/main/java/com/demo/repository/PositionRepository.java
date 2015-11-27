package com.demo.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.repository.CrudRepository;

import com.demo.model.Position;

@Transactional(TxType.MANDATORY)
public interface PositionRepository extends CrudRepository<Position, Long> {

	Position findFirstByGameIdAndPlayer(Long gameId, String player);

	Iterable<Position> findByGameId(Long gameId);
	
}
