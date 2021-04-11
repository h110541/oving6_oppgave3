package no.hvl.dat102;

import java.util.*;

public interface BSTreADT<T extends Comparable<T>> {

	/**
	 * Returnerer antall elementer i treet.
	 *
	 * @return antall elementer
	 */
	public int antall();

	/**
	 * Returnerer sann hvis treet ikke inneholder noen elementer, ellers usann.
	 *
	 * @return sann hvis treet er tomt, ellers usann
	 */
	public boolean erTom();

	/**
	 * Legger et element til på riktig plass i det binære søketreet.
	 *
	 * @param element objektet som skal legges til
	 */
	public void leggTil(T element);

	/**
	 * Returnerer en referanse til det spesifiserte elementet hvis det finnes i treet,
	 * ellers returneres null.
	 *
	 * @param element objektet som skal søkes etter
	 * @return referanse til angitt element (hvis det finnes i treet)
	 */
	public T finn(T element);

	/**
	 * Returnerer en referanse til det minste elementet i treet, null hvis treet er tomt.
	 *
	 * @return referanse til det minste elementet i treet
	 */
	public T finnMin();

	/**
	 * Returnerer en referanse til det største elementet i treet, null hvis treet er tomt.
	 *
	 * @return referanse til det største elementet i treet
	 */
	public T finnMaks();

	/**
	 * Fjerner et element fra dette treet hvis det fins, ellers returneres null.
	 *
	 * @param element objektet som skal fjernes
	 * @return referanse til elementet som fjernes
	 */
	// public T fjern( T element);
	// Ikke impelmentert

	/**
	 * Fjerner minste elementet fra treet hvis det ikke er tomt, ellers returneres null.
	 *
	 * @return referanse til elementet som fjernes
	 */
	public T fjernMin();

	/**
	 * Fjerner største elementet fra treet hvis det ikke er tomt, ellers returneres null.
	 *
	 * @return referanse til elementet som fjernes
	 */
	public T fjernMaks();
}
