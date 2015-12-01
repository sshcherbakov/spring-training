package com.demo.game.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.data.jpa.domain.AbstractPersistable;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(	name="t_player" )
public class Player extends AbstractPersistable<Long> {

	private static final long serialVersionUID = 1L;
	
	private String name;
	
	@Column(length=10)
	private Color color;
	
	@Column(length=10)
	private Piece piece;
	
	
	public Player() {
		this(null);
	}

	public Player(Long id) {
		this.setId(id);
	}
	
	
	@Override
	public String toString() {
		return String.format("[Player %d: %s %s %s", 
				getId(), getName(), getPiece(), getColor());
	}

	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Piece getPiece() {
		return piece;
	}

	public void setPiece(Piece piece) {
		this.piece = piece;
	}

	@JsonProperty("code")
	public String getPieceCode() {
		switch(piece) {
		case rook: 		return color == Color.white ? "&#9814;" : "&#9820;";
		case knight: 	return color == Color.white ? "&#9816;" : "&#9822;";
		case bishop: 	return color == Color.white ? "&#9815;" : "&#9821;";
		case king: 		return color == Color.white ? "&#9813;" : "&#9819;";
		case queen: 	return color == Color.white ? "&#9812;" : "&#9818;";
		case pawn: 		return color == Color.white ? "&#9817;" : "&#9823;";
		default:
			return "";
		}
	}

}
