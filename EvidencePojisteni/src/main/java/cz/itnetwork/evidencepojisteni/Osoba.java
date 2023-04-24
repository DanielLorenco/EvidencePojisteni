/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.evidencepojisteni;

/**
 *
 * @author danlo Třída pro reprezentaci osoby
 */
public class Osoba {

    private String jmeno;
    private String prijmeni;
    private int vek;
    private int telefonniCislo;

    public Osoba(String jmeno, String prijmeni, int vek, int telefonniCislo) {
        // Konstruktor pro inicializaci proměnných třídy
        this.jmeno = jmeno;
        this.prijmeni = prijmeni;
        this.vek = vek;
        this.telefonniCislo = telefonniCislo;
    }

    /**
     * @return the jmeno
     */
    public String getJmeno() {
        return jmeno;
    }

    /**
     * @param jmeno the jmeno to set
     */
    public void setJmeno(String jmeno) {
        this.jmeno = jmeno;
    }

    /**
     * @return the prijmeni
     */
    public String getPrijmeni() {
        return prijmeni;
    }

    /**
     * @param prijmeni the prijmeni to set
     */
    public void setPrijmeni(String prijmeni) {
        this.prijmeni = prijmeni;
    }

    /**
     * @return the vek
     */
    public int getVek() {
        return vek;
    }

    /**
     * @param vek the vek to set
     */
    public void setVek(int vek) {
        this.vek = vek;
    }

    /**
     * @return the telefonniCislo
     */
    public int getTelefonniCislo() {
        return telefonniCislo;
    }

    /**
     * @param telefonniCislo the telefonniCislo to set
     */
    public void setTelefonniCislo(int telefonniCislo) {
        this.telefonniCislo = telefonniCislo;
    }

    @Override
    public String toString() {
        // Metoda pro vypsání řetězce reprezentujícího osobu
        return String.format("%-20s%-20s%-8d%-20s", jmeno, prijmeni, vek, telefonniCislo);
    }
}
