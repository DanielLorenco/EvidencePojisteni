/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package cz.itnetwork.evidencepojisteni;

/**
 *
 * @author danlo Třída pro evidenci pojištění
 */
public class EvidencePojisteni {

    public static void main(String[] args) {
        // Vytvoří novou instanci Evidence a zavolá na ni metodu vypisUvodniObrazovku a vytiskniMenu
        Evidence evidence = new Evidence();
        evidence.vypisUvodniObrazovku();
        evidence.vytiskniMenu();
    }
}
