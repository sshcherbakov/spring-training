package com.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="t_game")
public class Game extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@JsonManagedReference
	@OneToMany(cascade = CascadeType.ALL, mappedBy="game")
	private List<Position> positions = new ArrayList<>();

	public Game() {
		this(null);
	}

	public Game(Long id) {
		this.setId(id);
	}

	public List<Position> getPositions() {
		return Collections.unmodifiableList(positions);
	}
	
	
	@Override
	public String toString() {
		return "[Game " + getId() + "]";
	}

	public void addPosition(Position pos) {
		positions.add(pos);
	}

}
