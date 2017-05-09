/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Wedstrijd;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UC6 {

    public static void testUC6(DomeinController dc) {
        dc.maakSetStapel();

        //test of setstapel werkt.
        //
        int speler1ScoreSetVoorSpel = dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(0));
        int speler2ScoreSetVoorSpel = dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(1));

//        System.out.println("Dit is begin setstapel");
//        int index = 1;
//
//        for (String kaart : dc.geefSetStapel()) {
//            System.out.printf("%d) %s%n", index, kaart);
//            index++;
//        }
        //mijn vraag: is het attr in wedstrijd aantalsets de bedoeling dat het blijft doorlopen 
        //of stopt het bij elke ronde? anders-->
        while (dc.setEinde() == false) {
            dc.bepaalSpelerAanVolgendeBeurt();
            //eigenlijk mag de dc.bepaalSpelerAanEersteBeurt weg !!

            //controle welke set ronde
            dc.voegBovensteKaartVanSetStapelToeAanSpelbord();

            //test setstapel na shufle ---> werkt
//            System.out.println("Dit is de setstapel na shuffle");
//            index = 1;
//            for (String kaart : dc.geefSetStapel()) {
//                System.out.printf("%d) %s%n", index, kaart);
//                index++;
//            }
            List<String> spelers = dc.geefGeregistreerdeSpelers();

            for (int i = 0; i < spelers.size(); i++) {
                System.out.printf("%nDit is het spelbord van %s", spelers.get(i));
                for (int k = 0; k < dc.geefSpelbord(spelers.get(i)).size(); k++) {
                    System.out.printf("%d) %s%n", i + 1, dc.geefSpelbord(spelers.get(i)));
                }

                System.out.printf("----------------");
                System.out.printf("%nsetscore van speler %s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                System.out.printf("Dit is de wedstrijdstapel van %s", spelers.get(i));

                for (int k = 0; k < dc.geefWedstrijdStapel(spelers.get(i)).size(); k++) {
                    System.out.printf("%d) %s%n", i + 1, dc.geefWedstrijdStapel(spelers.get(i)));
                }

            }

            String spelerAanBeurt = dc.geefActieveSpeler();
            System.out.printf("De actieve speler is %s%n", spelerAanBeurt);
            System.out.printf("-----------------%n");

            System.out.printf("Wat wilt u doen, %s? (antwoord met het bijhorende cijfer)%n", spelerAanBeurt);

            Scanner input = new Scanner(System.in);
            int menuKeuze = 0;
            System.out.println("1. Beëindig beurt%n");
            System.out.println("2. Gebruik wedstrijdkaart%n");
            System.out.println("3. Bevries spelbord%n");
            System.out.println(" > ");

            //ook mogelijk om menu niet altijd opnieuw te tonen
            while (menuKeuze < 1 || menuKeuze > 3) {
                boolean succes = false;
                while (!succes) {
                    try {
                        menuKeuze = input.nextInt();
                        succes = true;
                    } catch (InputMismatchException ex) {
                        System.err.println("Geef een getal in");
                        System.out.print(" > ");
                        input.nextLine();//is dit nodig?

                    }
                }
            }

            if (menuKeuze == 1) {
                dc.beeindigBeurt();
                //return
            }
            if (menuKeuze == 2) {
                int kaartKeuze = -1;
                System.out.println("Dit is je huidige wedstrijdstapel:%n");
                List<String> wedstrijdStapel = dc.geefWedstrijdStapel(spelerAanBeurt);
                if (wedstrijdStapel.size() > 0) {
                    for (int i = 0; i < wedstrijdStapel.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, wedstrijdStapel.get(i));
                    }
                } else {
                    System.out.println("Je hebt geen kaarten in je wedstrijdstapel.");
                }

                System.out.println("Welke kaart wil je opleggen:");
                while (kaartKeuze < 0 || kaartKeuze >= wedstrijdStapel.size()) {
                    boolean succes = false;
                    System.out.printf("Maak uw keuze(typ het cijfer)%n");
                    System.out.print(" > ");
                    while (!succes) {
                        try {
                            kaartKeuze = input.nextInt() - 1;
                            succes = true;
                        } catch (InputMismatchException ex) {
                            System.err.print("Geef een getal in");
                            System.out.print(" > ");
                            input.nextLine();
                        }
                    }
                }

                int keuzeType = 3;

                if (dc.geefKaartType(wedstrijdStapel.get(kaartKeuze)).equals("+/-")) {
                    boolean succes = false;
                    System.out.printf("Wilt u de min gebruiken plus gebruiken");
                    while (!succes) {
                        do {
                            System.out.printf("1. + ");
                            System.out.printf("2. - ");
                            try {

                                keuzeType = input.nextInt();
                                succes = true;
                            } catch (InputMismatchException ex) {
                                System.err.print("Geef een getal in");
                                System.out.print(" > ");
                                input.nextInt();
                            }
                        } while (keuzeType < 0 || keuzeType > 2);

                    }
                }

                dc.legWedstrijdkaart(wedstrijdStapel.get(kaartKeuze), keuzeType);
                System.out.println("%nDe kaart werd toegevoegd aan het spelbord.");
                //nog de wedstrijdsituatie tonen
                for (int i = 0; i < spelers.size(); i++) {
                    System.out.printf("%nDit is het spelbord van %s", spelers.get(i));
                    for (int k = 0; k < dc.geefSpelbord(spelers.get(i)).size(); k++) {
                        System.out.printf("%d) %s%n", i + 1, dc.geefSpelbord(spelers.get(i)));
                    }

                    System.out.printf("----------------");
                    System.out.printf("%nsetscore van speler %s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                    System.out.printf("Dit is de wedstrijdstapel van %s", spelers.get(i));

                    for (int k = 0; k < dc.geefWedstrijdStapel(spelers.get(i)).size(); k++) {
                        System.out.printf("%d) %s%n", i + 1, dc.geefWedstrijdStapel(spelers.get(i)));
                    }

                }

                //DE SPELER KIEST EEN WISSEL????
            }

            if (menuKeuze == 3) {
                dc.bevriesSpelbord();
            }
        }

        int speler1ScoreSetNaSpel = dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(0));
        int speler2ScoreSetNaSpel = dc.geefSetScore(dc.geefGeregistreerdeSpelers().get(1));

        String speler1 = dc.geefGeregistreerdeSpelers().get(0);
        String speler2 = dc.geefGeregistreerdeSpelers().get(1);

        if (speler1ScoreSetNaSpel > speler2ScoreSetNaSpel) {

            System.out.printf("De winnaar van de set is %s", speler1);
        }
        if (speler1ScoreSetNaSpel < speler2ScoreSetNaSpel) {
            System.out.printf("De winnaar van de set is %s", speler2);
        }
        if((speler1ScoreSetNaSpel - speler1ScoreSetVoorSpel) == (speler2ScoreSetNaSpel - speler2ScoreSetVoorSpel)){
            System.out.printf("Er is een gelijkspel, jullie zijn beide winnaars.");
        }//als vorige score gelijk is aan huidige

        dc.geefUitslag();

        if (dc.geefNaamWinnaar() == null) {
            System.out.println("Er is een gelijkspel");
        } else {
            System.out.printf("De winnaar van deze set is %s met een setScore van %d", dc.geefNaamWinnaar(), dc.geefSetScore(dc.geefNaamWinnaar()));

        }

    }

}
