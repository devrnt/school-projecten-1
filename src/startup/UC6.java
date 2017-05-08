/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Wedstrijd;
import java.util.List;
import java.util.Scanner;

public class UC6 {

    public static void testUC6(DomeinController dc, String speler) {
        dc.maakSetStapel();

        int index = 1;
        //tewt of setstapel werkt.
        System.out.println("Dit begin setstapel");

        for (String kaart : dc.geefSetStapel()) {
            System.out.printf("%d) %s%n", index, kaart);
            index++;
        }

        while (dc.setEinde() == false) {
            dc.bepaalSpelerAanDeBeurtEersteSet();
            //controle set

            String spelerAanBeurt = dc.geefActieveSpeler();

            dc.voegBovensteKaartVanSetStapelToeAanSpelbord();

            System.out.println("Dit is de setstapel na shuffle");
            index = 1;
            for (String kaart : dc.geefSetStapel()) {
                System.out.printf("%d) %s%n", index, kaart);
                index++;
            }

            System.out.println("Dit is het spelbord van :");

            List<String> spelers = dc.geefGeregistreerdeSpelers();
            for (int i = 0; i < spelers.size(); i++) {
                System.out.printf("%nDit is spelbord van %s", spelers.get(i));
                for (String kaart : dc.geefSpelbord(spelers.get(i))) {
                    int kaartnr = 1;

                    System.out.printf("%d) %s%n", kaartnr, kaart);
                    kaartnr++;
                }
                System.out.printf("setscore van speler %s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                System.out.printf("Dit is de wedstrijdstapel van %s", spelers.get(i));

                for (String kaart : dc.geefWedstrijdStapel(spelers.get(i))) {
                    int kaartnr = 1;
                    System.out.printf("%d) %s%n", kaartnr, kaart);
                    kaartnr++;
                }

            }

            System.out.printf("De actieve speler is %s%n", spelerAanBeurt);

            System.out.printf("Wat wilt u doen, %s", spelerAanBeurt);

            int keuze = 0;

            Scanner input = new Scanner(System.in);
            while (keuze < 1 || keuze > 3) {
                System.out.println("1.) beurt beëindigen");
                System.out.println("2.) wedstrijdkaart gebruiken");
                System.out.println("3.) bevries spelbord");
                System.out.println(" >");
                keuze = input.nextInt();
            }

            if (keuze == 1) {
                dc.beeindigBeurt();
            }
            if (keuze == 2) {
                int kaartKeuze = -1;
                System.out.println("Dit is je wedstrijdstapel:");
                List<String> wedstrijdStapel = dc.geefWedstrijdStapel(spelerAanBeurt);
                for (int i = 0; i < wedstrijdStapel.size(); i++) {
                    System.out.printf("%d) %s", i + 1, wedstrijdStapel.get(i));
                }

                System.out.println("Welke kaart wil je opleggen:");
                while (kaartKeuze < 0 || kaartKeuze >= wedstrijdStapel.size()) {
                    System.out.printf("Maak uw keuze%n");
                    System.out.print(" > ");
                    kaartKeuze = input.nextInt() - 1;
                }
//            dc.legWedstrijdkaart(wedstrijdStapel.equals(wedstrijdStapel.get(i)); METHODE SCHRIJVEN VOOR wedstrijdkaart te leggen op vna string naar Kaart
                dc.legWedstrijdkaart(wedstrijdStapel.get(kaartKeuze));
                System.out.println("De kaart werd toegevoegd aan het spelbord.");
                
                
                
                //DE SPELER KIEST EEN WISSEL????
            }

            if (keuze == 3) {
                dc.bevriesSpelbord();
                
            }

            // met de while een input geven met de keuze voor beurt te beindingen,  bevriezen, wedstrijdkaart gebruiken.
        }

        dc.geefUitslag();

        if (dc.geefNaamWinnaar() == null) {
            System.out.println("Er is een gelijkspel");
        } else {
            System.out.printf("De winnaar van deze set is %s", dc.geefNaamWinnaar());

        }

    }

}
