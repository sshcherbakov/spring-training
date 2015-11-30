package com.demo.deepthought.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

// TODO: D1. Mark JPA entity
// 		and implement convenience Spring Data AbstractPersistable 
public class Protokoll {
	
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm a z")
	private Date timestamp;
	private String anfragesteller;
	private String antwort;

	public Protokoll() {

	}

	public Protokoll(final String string) {
		antwort = string;
	}

	@Override
	public boolean equals(final Object other) {
		final Protokoll otherAntwort = (Protokoll) other;
		return otherAntwort.getAntwort().equals(this.getAntwort()) && 
			otherAntwort.getAnfragesteller().equals(this.getAnfragesteller()) &&
			otherAntwort.getTimestamp().equals(this.getTimestamp());
	}

	@Override
	public int hashCode() {
		return this.getAntwort().hashCode() 
			+ this.getAnfragesteller().hashCode() 
			+ this.getTimestamp().hashCode() 
			+ 42;
	}

	@Override
	public String toString() {
		return "Der " + this.getAnfragesteller() + "hat eine Antwort <" + this.getAntwort() 
			+ "> um " + this.getTimestamp() + " bekommen";
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
	
	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

}