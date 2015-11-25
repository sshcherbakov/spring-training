package com.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity(name="ANTWORT")
public class Antwort extends AbstractPersistable<Integer> {

	private static final long serialVersionUID = 1L;
	
	private transient String anfragesteller;
	
	@Column(name="val")
	private String antwort;

	
	public Antwort() {
		this(null);
	}

	public Antwort(Integer id) {
		this.setId(id);
	}

	
	
	@Override
	public String toString() {
		return "Hallo " + this.getAnfragesteller() + ", die Antwort ist: " + this.getAntwort();
	}

	
	public String getAntwort() {
		return antwort;
	}

	public void setAntwort(final String antwort) {
		this.antwort = antwort;
	}

	public String getAnfragesteller() {
		return anfragesteller;
	}

	public void setAnfragesteller(final String anfragesteller) {
		this.anfragesteller = anfragesteller;
	}
}
