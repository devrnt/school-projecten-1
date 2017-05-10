/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Kaart;
import domein.Speler;
import domein.SpelerRepository;
import domein.Wedstrijd;
import java.util.List;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class test {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            List<Kaart> allekaarten = dc.geefAlleKaarten();
            for (Kaart allekrt : allekaarten) {
                if (allekrt.getType().equals("+"))
                    System.out.println(allekrt.getOmschrijving());
                
            }

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
