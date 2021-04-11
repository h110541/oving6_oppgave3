package no.hvl.dat102;

import java.util.Iterator;

//********************************************************************
// KjedetBinærSøkeTre.java
//
//********************************************************************

public class KjedetBSTre<T extends Comparable<T>> implements BSTreADT<T>,Iterable<T> {

	private int antall;
	private BinaerTreNode<T> rot;

	/**
	 * @return the rot
	 */
	public BinaerTreNode<T> getRot() {
		return rot;
	}

	/**
	 * @param rot the rot to set
	 */
	public void setRot(BinaerTreNode<T> rot) {
		this.rot = rot;
	}

	/******************************************************************
	 * Oppretter et tomt binært søketre.
	 ******************************************************************/
	public KjedetBSTre() {
		antall = 0;
		rot = null;
	}

	/******************************************************************
	 * Oppretter et binært søketre med en node..
	 ******************************************************************/
	public KjedetBSTre(T element) {
		rot = new BinaerTreNode<T>(element);
		antall = 1;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære trett er tomt og usann ellers.
	 *****************************************************************/
	@Override
	public int antall() {
		return antall;
	}

	/*****************************************************************
	 * Returnerer sann hvis dette binære treet er tom og usann ellers.
	 *****************************************************************/
	@Override
	public boolean erTom() {
		return (antall == 0);
	}


	/**********************************************************************
	 * Legger det spesifiserte elementet på passende plass i BS-treet. Like
	 * elementer blir lagt til høyre. Bruk av rekursiv hjelpemetode.
	 ********************************************************************/
	@Override
	public void leggTil(T element) {
		rot = leggTilRek(rot, element);
		antall++;
	}

	private BinaerTreNode<T> leggTilRek(BinaerTreNode<T> p, T element) {

		if (antall == 0)
			return (new BinaerTreNode<T>(element));

		if (element.compareTo(p.getElement()) < 0) {
			BinaerTreNode<T> venstre = p.getVenstre();

			if (venstre == null)
				p.setVenstre(new BinaerTreNode<T>(element));
			else
				leggTilRek(venstre, element);

		} else {
			BinaerTreNode<T> hoyre = p.getHoyre();

			if (hoyre == null)
				p.setHoyre(new BinaerTreNode<T>(element));
			else
				leggTilRek(hoyre, element);
		}

		return p;
	}

	/******************************************************************
	 * Fjerner noden med minste verdi fra dette binære søketreet.
	 *********************************************************************/
	@Override
	public T fjernMin() {
		if (antall == 0)
			return null;

		T resultat = null;

		if (rot.getVenstre() == null) {
			resultat = rot.getElement();
			rot = rot.getHoyre();
		} else {
			BinaerTreNode<T> parent = rot;
			BinaerTreNode<T> current = rot.getVenstre();

			while (current.getVenstre() != null) {
				parent = current;
				current = current.getVenstre();
			}

			resultat = current.getElement();
			parent.setVenstre(current.getHoyre());
		}

		antall--;
		return resultat;
	}//

	/******************************************************************
	 * Fjerner noden med største verdi fra dette binære søketreet.
	 ******************************************************************/
	@Override
	public T fjernMaks() {
		if (antall == 0)
			return null;

		T resultat = null;

		if (rot.getHoyre() == null) {
			resultat = rot.getElement();
			rot = rot.getVenstre();
		} else {
			BinaerTreNode<T> parent = rot;
			BinaerTreNode<T> current = rot.getHoyre();

			while (current.getHoyre() != null) {
				parent = current;
				current = current.getHoyre();
			}

			resultat = current.getElement();
			parent.setHoyre(current.getVenstre());
		}

		antall--;
		return resultat;
	}//

	/******************************************************************
	 * Returnerer det minste elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMin() {
		if (antall == 0)
			return null;

		BinaerTreNode<T> node = rot;

		while (node.getVenstre() != null)
			node = node.getVenstre();

		return node.getElement();
	}//

	/******************************************************************
	 * Returnerer det største elementet i dette binære søketreet.
	 ******************************************************************/
	@Override
	public T finnMaks() {
		if (antall == 0)
			return null;

		BinaerTreNode<T> node = rot;

		while (node.getHoyre() != null)
			node = node.getHoyre();

		return node.getElement();
	}//

	/*******************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det finst i dette
	 * BS-treet, null ellers. Bruk av rekursjon /
	 ******************************************************************************/
	@Override
	public T finn(T element) {
		// Søk med rekursiv hjelpemetode

		return finnRek(element, rot);
	}

	private T finnRek(T element, BinaerTreNode<T> node) {
		if (node == null)
			return null;

		T resultat = null;
		int comp = element.compareTo(node.getElement());

		if (comp == 0)
			resultat = node.getElement();
		else if (comp < 0)
			resultat = finnRek(element, node.getVenstre());
		else
			resultat = finnRek(element, node.getHoyre());

		return resultat;
	}

	/************************************************************************
	 * Returnerer en referanse til det spesifiserte elementet hvis det fins i dette
	 * BS-treet, null ellers. Uten bruk av rekursjon. /
	 ************************************************************************/
	public T finn2(T element) {
		BinaerTreNode<T> node = rot;
		T resultat = null;

		while (node != null && resultat == null) {
			int comp = element.compareTo(node.getElement());

			if (comp == 0)
				resultat = node.getElement();
			else if (comp < 0)
				node = node.getVenstre();
			else
				node = node.getHoyre();
		}

		return resultat;
	}

	public int hoyde() {
		if (antall == 0)
			return -1;

		return hoydeRek(rot);
	}

	private int hoydeRek(BinaerTreNode<T> node) {
		if (node == null)
			return -1;

		int venstre = hoydeRek(node.getVenstre());
		int hoyre = hoydeRek(node.getHoyre());

		return (1 + Integer.max(venstre, hoyre));
	}

	public void visInorden() {
		visInorden(rot);
		System.out.println();
	}

	private void visInorden(BinaerTreNode<T> p) {
		if (p != null) {
			visInorden(p.getVenstre());
			System.out.print(" " + p.getElement());
			visInorden(p.getHoyre());
		}
	}

	@Override
	public Iterator<T> iterator() {
		return new InordenIterator<T>(rot);

	}
}// class
