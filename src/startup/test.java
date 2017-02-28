/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.Speler;

/**
 *
 * @author Jonas
 */
public class test {
    public static void main(String[] args) {
        Speler speler = new Speler("Te", 1990, 5);
        System.out.println(speler.getGebruikersnaam());
    }
}
