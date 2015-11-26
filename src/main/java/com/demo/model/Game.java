package com.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
public class Game extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;

	@OneToMany(cascade = CascadeType.ALL)
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
