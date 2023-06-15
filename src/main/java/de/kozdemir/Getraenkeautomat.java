package de.kozdemir;

import java.util.*;

public class Getraenkeautomat implements GetraenkeautomatInterface {
	private Map<Getraenk, Integer> warenbestand;
	private Map<Integer, Integer> muenzbestand;

	public Getraenkeautomat() {
		warenbestand = new HashMap<>();
		muenzbestand = new HashMap<>();
	}

	@Override
	public void getraenkHinzufuegen(Getraenk getraenk, int anzahl) {
		warenbestand.put(getraenk,anzahl);
	}

	@Override
	public void muenzeHinzufuegen(int wert, int anzahl) {
		muenzbestand.put(wert, anzahl);
	}

	@Override
	public GetraenkUndWechselgeld kaufen(Getraenk getraenk, List<Muenze> einzahlung) {
		int getraenkPreis = getraenk.getPreis();
		int einzahlungWert = 0;

		for (Muenze muenze : einzahlung) {
			einzahlungWert += muenze.getWert();
		}

		//Reicht das bezahlte Geld aus, um das Produkt kaufen zu können?
		if (getraenkPreis > einzahlungWert) {
			return new GetraenkUndWechselgeld(null, einzahlung);

		}

		//ist gewünschtes Produkt in der Getränkeautomat?
		if (!warenbestand.containsKey(getraenk) || warenbestand.get(getraenk) == 0) {
			return new GetraenkUndWechselgeld(null, einzahlung);
		}


		//berechnen wechseln Geld
		int wechselgeld = einzahlungWert - getraenkPreis;
		List<Muenze> herausgegebenesWechselgeld = berechneWechselgeld(wechselgeld);

		if (herausgegebenesWechselgeld == null) {
			return new GetraenkUndWechselgeld(null, einzahlung);
		}

		warenbestand.put(getraenk, warenbestand.get(getraenk) - 1);

		// Fügen wir das gegebene Geld zu den Münzen im Automaten hinzu
		for (Muenze muenze : einzahlung) {
			muenzbestand.put(muenze.getWert(), muenzbestand.getOrDefault(muenze.getWert(), 0) + 1);
		}

		return new GetraenkUndWechselgeld(getraenk, herausgegebenesWechselgeld);
	}

	@Override
	public void geldAbladen() {
		//Todo: ???
		//Wir leeren alle Münzen aus dem Automaten
		muenzbestand.clear();
	}

	private List<Muenze> berechneWechselgeld(int betrag) {
		List<Muenze> wechselgeld = new ArrayList<>();
		int[] muenzwerte = {200, 100, 50, 20, 10};

		for (int muenzwert : muenzwerte) {
			int anzahl = betrag / muenzwert;
			int verfuegbareMuenzen = muenzbestand.getOrDefault(muenzwert, 0);

			anzahl = Math.min(anzahl, verfuegbareMuenzen);

			for (int i = 0; i < anzahl; i++) {
				wechselgeld.add(new Muenze(muenzwert));
			}

			betrag -= anzahl * muenzwert;
		}

		if (betrag == 0) {
			for (Muenze muenze : wechselgeld) {
				muenzbestand.put(muenze.getWert(), muenzbestand.get(muenze.getWert()) - 1);
			}

			return wechselgeld;
		}

		return null;
	}


}
