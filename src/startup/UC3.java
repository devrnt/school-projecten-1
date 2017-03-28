/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Speler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import resources.Taal;

/**
 *
 * @author Edward
 */
public class UC3 {

    public static void testUC3(DomeinController dc) {
        //spelerlijst lokaal bijhouden en spelers die al geregistreerd zijn verwijderen eruit
        Scanner input = new Scanner(System.in);
        dc.maakWedstrijd();
        // String keuze = "";
        List<String> gekozenSpelers = new ArrayList<>();
        List <String> beschikbareSpelers = dc.geefLijstBeschikbareSpelers();
        
        
        if(beschikbareSpelers.isEmpty()){
            System.out.printf(dc.getTaal().getVertaling("geen_beschikbare_spelers") + "%n%n");
            return;
        }
        
        while (gekozenSpelers.size() < 2) {
            System.out.println(dc.getTaal().getVertaling("beschikbare_spelers"));
            int keuze = -1;
            while (keuze < 0 || keuze >= beschikbareSpelers.size()) {
                for (int i = 0; i < beschikbareSpelers.size(); i++) {
                    System.out.println(i + 1 + " " + beschikbareSpelers.get(i));
                }
                keuze = input.nextInt()-1;
            }
            gekozenSpelers.add(beschikbareSpelers.get(keuze));
            beschikbareSpelers.remove(keuze);
        }

        for (int i = 0; i < gekozenSpelers.size(); i++) {

            dc.registreerSpeler(gekozenSpelers.get(i));
        }

        System.out.println(dc.getTaal().getVertaling("geregistreerde_spelers"));
        dc.geefGeregistreerdeSpelers().forEach((naam) -> {
            System.out.println(naam);
        });

        List<String> spelersZonderStapel = dc.geefSpelerZonderWedstrijdStapel();

        while (spelersZonderStapel.size() > 0) {
            System.out.println(dc.getTaal().getVertaling("geen_wedstrijdstapel"));
            int keuze = -1;
            while (keuze < 0 || keuze >= spelersZonderStapel.size()) {
                for (int i = 0; i < spelersZonderStapel.size(); i++) {
                    System.out.println(i + " " + spelersZonderStapel.get(i));
                }
                keuze = input.nextInt();
            }
            UC4.testUC4(dc, spelersZonderStapel.get(keuze));

            spelersZonderStapel.remove(spelersZonderStapel.get(keuze));

            //System.out.println(dc.getTaal().getVertaling("geregistreerde_spelers"));
            //spelers.forEach((naam)->{ System.out.println(naam); });
        }

    }
}
