package com.demo.model;


/**
 *
 * @author Tobias Wolf
 */
public class Antwort {

	private String anfragesteller;
	private String antwort;

	public Antwort() {

	}

	public Antwort(final String string) {
		antwort = string;
	}

	@Override
	public boolean equals(final Object other) {
		final Antwort otherAntwort = (Antwort) other;
		return (otherAntwort.getAntwort().equals(this.getAntwort()) && (otherAntwort.getAnfragesteller().equals(this.getAnfragesteller())));
	}

	@Override
	public int hashCode() {
		return this.getAntwort().hashCode() + this.getAnfragesteller().hashCode() + 42;
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
