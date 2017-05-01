/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class UC9 {

    public static void testUC9(DomeinController dc) {
        Scanner in = new Scanner(System.in);

        List<String> wedstrijdLijst = dc.toonLijstWedstrijden();

        System.out.println(dc.getTaal().getVertaling("lijst_wedstrijden"));
        for (int i = 0; i < wedstrijdLijst.size(); i++) {
            System.out.println(i + 1 + " " + wedstrijdLijst.get(i));
        }
        System.out.printf(" > ");
        int keuze = in.nextInt() - 1;
        System.out.println();
        
        dc.laadWedstrijd(wedstrijdLijst.get(keuze));
        System.out.println(dc.getTaal().getVertaling("laden_gelukt"));
        //nu UC5 oproepen?
    }
}
