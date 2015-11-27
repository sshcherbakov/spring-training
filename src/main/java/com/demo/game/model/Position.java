package com.demo.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(
	name="t_position",
	uniqueConstraints=@UniqueConstraint(columnNames = {"game_id", "player"}))
public class Position extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;
	
	@JsonBackReference
	@ManyToOne
	private Game game;

	private String player;
	
	@Column(length=2)
	private String position;

	
	
	public Position() {
		this(null);
	}

	public Position(Long id) {
		this.setId(id);
	}

	
	
	@Override
	public String toString() {
		return String.format("[Position %d: %s%s %s %s", 
				getId(), getPosition(), getGame(), getPlayer());
	}

	
	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public Game getGame() {
		return game;
	}
	
	public void setGame(Game game) {
		this.game = game;
	}
	
	public String getPlayer() {
		return player;
	}

	public void setPlayer(String player) {
		this.player = player;
	}

	
}
