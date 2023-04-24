/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package cz.itnetwork.evidencepojisteni;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author danlo Třída Evidence reprezentuje aplikaci pro evidenci pojištění.
 */
public class Evidence {

    Scanner sc = new Scanner(System.in, "Windows-1250");
    Databaze databaze = new Databaze();
    public static final DateTimeFormatter FORMAT_DATA = DateTimeFormatter.ofPattern("d'.'M'.'y H:mm");
    private final LocalDateTime dnes = LocalDateTime.now();

    /**
     * Metoda pro vypsání úvodní obrazovky aplikace.
     */
    public void vypisUvodniObrazovku() {

        System.out.println("------------------------------------------");
        System.out.println("Vítejte v aplikaci pro evidenci pojištění");
        System.out.println("------------------------------------------");
        System.out.println("------------------------------------------");
        System.out.printf("Dnes je %s\n", FORMAT_DATA.format(dnes));
        System.out.println();
    }

    /**
     * Metoda pro vytisknutí hlavního menu aplikace a obsluhu uživatelského
     * vstupu.
     */
    public void vytiskniMenu() {

        String volba = "";

        while (!volba.equals("4")) { // opakování cyklu, dokud uživatel nezvolí možnost Konec

            System.out.println("------------------------------------------");
            System.out.println("Evidence pojištěných");
            System.out.println("------------------------------------------");
            System.out.println("Vyberte si akci:");
            System.out.println("1 - Přidat nového pojištěného");
            System.out.println("2 - Vypsat všechny pojištěné");
            System.out.println("3 - Vyhledat pojištěného");
            System.out.println("4 - Konec");
            System.out.println("------------------------------------------");
            volba = sc.nextLine();

            switch (volba) {
                case "1" ->
                    pridejPojisteneho();
                case "2" ->
                    vypisVsechnyPojistene();
                case "3" ->
                    vyhledejPojisteneho();
                case "4" -> {
                    System.out.print("Pro ukončení programu stisněte ENTER...");
                    sc.nextLine();
                }
                default -> {
                    System.out.print("Neplatná volba, stiskněte klávesu ENTER a opakujte volbu.");
                    sc.nextLine();
                }
            }
        }
    }

    /**
     * Metoda pro přidání pojištěné osoby do databáze
     */
    public void pridejPojisteneho() {
        String jmeno = zadejJmeno(); // zavolání metody pro zadání jména od uživatele
        String prijmeni = zadejPrijmeni(); // zavolání metody pro zadání příjmení od uživatele

        int vek;
        do {
            System.out.println("Zadejte věk pojištěného: ");

            try {
                vek = Integer.parseInt(sc.nextLine());
                if (vek < 0 || vek > 150) {
                    throw new IllegalArgumentException("Věk musí být mezi 0 a 150"); //chybová hláška pro špatnou hodnotu věku
                }
            } catch (NumberFormatException e) { //chybová hláška pro špatný formát věku
                System.out.println("Věk musí být celé číslo.");
                vek = -1;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                vek = -1;
            }
        } while (vek < 0);

        int telefonniCislo;
        do {
            System.out.println("Zadejte telefonní číslo pojištěného: ");
            try {
                telefonniCislo = Integer.parseInt(sc.nextLine()); //zadání telefonního čísla uživatelem
            } catch (NumberFormatException e) {
                System.out.println("Telefonní číslo musí být celé číslo."); //chybová hláška pro špatný formát telefonního čísla
                telefonniCislo = -1;
            }
        } while (telefonniCislo < 0);

        try {
            databaze.pridejPojisteneho(jmeno, prijmeni, vek, telefonniCislo); //přidání pojištěného do databáze
            System.out.print("Data byla uložena. Pokračujte klávesou ENTER...");
            sc.nextLine();
        } catch (Exception e) { // chybová hláška pro chybu při přidávání do databáze
            System.out.println("Došlo k chybě při přidávání pojištěné osoby: " + e.getMessage());
        }
    }

    /**
     * Metoda pro vypsání všech pojištěných osob
     */
    public void vypisVsechnyPojistene() {
        ArrayList<Osoba> pojisteneOsoby = databaze.vypisVsechnyPojistene(); //Načtení všech pojištěných osob z databáze
        System.out.println("------------------------------------------");
        System.out.format("%-20s%-20s%-8s%-20s%n", "Jméno", "Příjmení", "Věk", "Telefonní číslo");
        for (Osoba osoba : pojisteneOsoby) { //výpis informací o každé pojištěné osobě
            System.out.println(osoba);
        }
        System.out.println("------------------------------------------");
        System.out.printf("Vygenerováno %s\n", FORMAT_DATA.format(dnes));
        System.out.println("------------------------------------------");
        System.out.println();
    }

    /**
     * Metoda pro vyhledání pojištěné osoby podle jména a příjmení
     */
    public void vyhledejPojisteneho() {
        ArrayList<Osoba> pojisteneOsoby = databaze.vypisVsechnyPojistene(); //Načtení všech pojištěných osob z databáze
        String hledaneJmeno = zadejJmeno(); // zavolání metody pro zadání jména od uživatele
        String hledanePrijmeni = zadejPrijmeni(); // zavolání metody pro zadání příjmení od uživatele

        System.out.println();

        String celeJmeno = hledaneJmeno + " " + hledanePrijmeni;
        boolean nalezeno = false;

        for (Osoba osoba : pojisteneOsoby) {
            String s = osoba.getJmeno() + " " + osoba.getPrijmeni();
            if (celeJmeno.equalsIgnoreCase(s)) { //ošetření velkých a malých písmen
                if (!nalezeno) {
                    System.out.format("%-20s%-20s%-8s%-20s%n", "Jméno", "Příjmení", "Věk", "Telefonní číslo");
                    nalezeno = true;
                }
                System.out.println(osoba); //výpis nalezených osob
            }
        }

        if (!nalezeno) {
            System.out.println("Nebyl nalezen žádný pojištěný s tímto jménem a příjmením");
        }

    }

    /**
     * Metoda pro zadání jméno pojištěného
     *
     * @return jmeno jméno pojištěného
     */
    public String zadejJmeno() {
        System.out.println("Zadejte jméno pojištěného: ");
        String jmeno = "";
        do {
            jmeno = sc.nextLine().trim();
            while (jmeno.isEmpty()) {
                System.out.println("Jméno musí být vyplněno. Zadejte prosím jméno pojištěného: ");
                jmeno = sc.nextLine().trim();
            }
            if (jmeno.matches(".*\\d.*")) {
                System.out.println("Jméno nesmí obsahovat čísla. Zadejte prosím platné jméno: ");
            }
        } while (jmeno.matches(".*\\d.*"));
        return jmeno;
    }

    /**
     * Metoda pro zadání příjmení pojištěného
     *
     * @return prijmeni příjmení pojištěného
     */
    public String zadejPrijmeni() {
        System.out.println("Zadejte příjmení pojištěného: ");
        String prijmeni = "";
        do {
            prijmeni = sc.nextLine().trim();
            while (prijmeni.isEmpty()) {
                System.out.println("Příjmení musí být vyplněno. Zadejte prosím příjmení pojištěného: ");
                prijmeni = sc.nextLine().trim();
            }
            if (prijmeni.matches(".*\\d.*")) {
                System.out.println("Příjmení nesmí obsahovat čísla. Zadejte prosím platné příjmení: ");
            }
        } while (prijmeni.matches(".*\\d.*"));
        return prijmeni;
    }

}
