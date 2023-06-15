package de.kozdemir;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Getraenkeautomat automat = new Getraenkeautomat();

        // Getränkebestand hinzufügen
        automat.getraenkHinzufuegen(new Getraenk(1,"Cola", 120), 5);
        automat.getraenkHinzufuegen(new Getraenk(2,"Wasser", 80), 10);
        automat.getraenkHinzufuegen(new Getraenk(3,"Saft", 150), 3);
        automat.getraenkHinzufuegen(new Getraenk(4,"Cola", 120), 5);

        // Münzbestand hinzufügen
        automat.muenzeHinzufuegen(10, 100);
        automat.muenzeHinzufuegen(20, 100);
        automat.muenzeHinzufuegen(50, 50);
        automat.muenzeHinzufuegen(100, 50);
        automat.muenzeHinzufuegen(200, 20);


        // Getränk kaufen
        Getraenk gewuenschtesGetraenk = new Getraenk(1);
        List<Muenze> einzahlung = new ArrayList<>();
        einzahlung.add(new Muenze(200)); // 2 Euro einwerfen

        GetraenkUndWechselgeld ergebnis = automat.kaufen(gewuenschtesGetraenk, einzahlung);

        if (ergebnis.getGetraenk() != null) {
            System.out.println("Getränk erhalten: " + ergebnis.getGetraenk().getName());

            System.out.println("Wechselgeld erhalten:");
            for (Muenze muenze : ergebnis.getWechselgeld()) {
                System.out.println(muenze.getWert() + " Cent");
            }
        } else {
            System.out.println("Fehler: Getränk nicht verfügbar oder unzureichende Einzahlung.");
        }
    }
}
