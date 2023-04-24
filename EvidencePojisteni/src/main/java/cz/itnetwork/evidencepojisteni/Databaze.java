/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.evidencepojisteni;

import java.util.ArrayList;

/**
 *
 * @author danlo Třída Databaze reprezentuje databázi pojistných osob.
 */
public class Databaze {
// Seznam všech pojištěných osob

    private final ArrayList<Osoba> pojisteneOsoby = new ArrayList();

    /**
     * Metoda pro přidání nové pojištěné osoby do databáze.
     *
     * @param jmeno křestní jméno pojištěného
     * @param prijmeni příjmení pojištěného
     * @param vek věk pojištěného
     * @param telefonniCislo telefonní číslo pojištěného
     */
    public void pridejPojisteneho(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        // 
        pojisteneOsoby.add(new Osoba(jmeno, prijmeni, vek, telefonniCislo));
    }

    /**
     * Metoda pro vypsání všech pojištěných osob v databázi.
     *
     * @return seznam pojištěných osob
     */
    protected ArrayList<Osoba> vypisVsechnyPojistene() {
        return new ArrayList<>(pojisteneOsoby);
    }

}
