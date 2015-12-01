package com.demo.game.repository;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.game.model.Player;

@Transactional(TxType.MANDATORY)
public interface PlayerRepository extends JpaRepository<Player, Long> {

	Player findFirstByName(String player);
	
}
