package com.demo.repository;

public interface IVogonprotokollRepository {

	boolean erzeugeVogonSitzungsprotokoll();

	boolean istVogonSitzungsprotokollVorhanden();

	boolean entferneVogonSitzungsprotokolle();

	boolean aktualisiereVogonSitzungsprotokoll();
}
