package no.hvl.dat102;

import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

// Oppgave 3b)
public class Klientprogram {

	private final static int ANTALL_BSTRE = 100;
	private final static int ANTALL_NODER = 1023;

	private static Random rnd = new Random();
	private static ArrayList<KjedetBSTre<Integer>> treListe;

	public static void main(String[] args) {

		genererTreListe();
		skrivOutput();
	}

	private static void genererTreListe() {
		treListe = new ArrayList<KjedetBSTre<Integer>>();

		for (int i = 1; i <= ANTALL_BSTRE; i++) {
			KjedetBSTre<Integer> tre = new KjedetBSTre<>();

			for (int j = 1; j <= ANTALL_NODER; j++) {
				tre.leggTil(rnd.nextInt());
			}

			treListe.add(tre);
		}
	}

	private static void skrivOutput() {
		System.out.format("Antall trær: %d%n", ANTALL_BSTRE);
		System.out.format("Antall noder: %d%n", ANTALL_NODER);
		System.out.format("Minimal teoretisk høyde: %d%n", minimumHoyde(ANTALL_NODER));
		System.out.format("Maksimal teoretisk høyde: %d%n", ANTALL_NODER - 1);
		System.out.format("Minste høyde i løpet av kjøringene: %d%n", finnMinsteHoyde());
		System.out.format("Største høyde i løpet av kjøringene: %d%n", finnStorsteHoyde());
		System.out.format("Gjennomsnittlig høyde av alle kjøringene: %d%n", finnGjennomsnittsHoyde());
	}

	private static int minimumHoyde(int noder) {
		if (noder == 0)
			return -1;

		return (int) (Math.log(noder) / Math.log(2));
	}

	private static int finnMinsteHoyde() {
		int minst = Integer.MAX_VALUE;

		for (KjedetBSTre<Integer> tre : treListe) {
			int hoyde = tre.hoyde();

			if (hoyde < minst)
				minst = hoyde;
		}

		return minst;
	}

	private static int finnStorsteHoyde() {
		int maks = -1;

		for (KjedetBSTre<Integer> tre : treListe) {
			int hoyde = tre.hoyde();

			if (hoyde > maks)
				maks = hoyde;
		}

		return maks;
	}

	private static int finnGjennomsnittsHoyde() {
		int sum = 0;

		for (KjedetBSTre<Integer> tre : treListe)
			sum += tre.hoyde();

		return (sum / ANTALL_BSTRE);
	}

}
