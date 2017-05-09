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
        while (!dc.setEinde()) {
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
                System.out.println("------------------------");
                System.out.println("------------------------");
                if (dc.geefSpelbord(spelers.get(i)).isEmpty()) {
                    System.out.printf("%n" + " %s " + dc.getTaal().getVertaling("spelbord"), spelers.get(i));
                } else {
                    System.out.printf(dc.getTaal().getVertaling("spelbord_not_empty") + " %n" +  spelers.get(i));

                    List<String> spelbord = dc.geefSpelbord(spelers.get(i));

                    for (int k = 0; k < spelbord.size(); k++) {
                        System.out.println(k + 1 + ") " + spelbord.get(k));
                    }

//                    for (int k = 0; k < dc.geefSpelbord(spelers.get(i)).size(); k++) {
//                        System.out.printf("%d) %s%n", k + 1, dc.geefSpelbord(spelers.get(i)));
//                        //wordt nog niet goed getoond
//                        //ik denk dat forlus weg mag
//                    }
                }

                System.out.printf("----------------%n");
                System.out.printf(dc.getTaal().getVertaling("setscore") + " %s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                System.out.printf(dc.getTaal().getVertaling("wedstrijd_stapel") + " %s%n", spelers.get(i));

                List<String> wedstrijdStapel = dc.geefWedstrijdStapel(spelers.get(i));

                for (int k = 0; k < wedstrijdStapel.size(); k++) {
                    System.out.println(k + 1 + ") " + wedstrijdStapel.get(k));
                }

                System.out.println("");
                System.out.println("");

            }

            String spelerAanBeurt = dc.geefActieveSpeler();
            System.out.printf(dc.getTaal().getVertaling("actieve_speler") + "%s%n", spelerAanBeurt);
            System.out.printf("-----------------%n");

            System.out.printf(dc.getTaal().getVertaling("speler_aan_beurt_keuze") + " %s?%n", spelerAanBeurt);

            Scanner input = new Scanner(System.in);
            int menuKeuze = 0;
//            System.out.println(dc.getTaal().getVertaling("kaart_kopen"));
            System.out.println("1. " + dc.getTaal().getVertaling("beëindig_beurt"));
            System.out.println("2. " + dc.getTaal().getVertaling("gebruik_wedstrijdkaart"));
            System.out.println("3. " + dc.getTaal().getVertaling("bevries_spelbord"));
            System.out.print(" > ");

            //ook mogelijk om menu niet altijd opnieuw te tonen
            while (menuKeuze < 1 || menuKeuze > 3) {
                boolean succes = false;
                while (!succes) {
                    try {
                        menuKeuze = input.nextInt();
                        succes = true;
                    } catch (InputMismatchException ex) {
                        System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + ex.getMessage());
                        System.out.print(" > ");
                        input.nextLine();

                    }
                }
            }

            if (menuKeuze == 1) {
                dc.beeindigBeurt();//werkt nog niet
                return;
            }
            if (menuKeuze == 2) {
                int kaartKeuze = -1;

                System.out.println(dc.getTaal().getVertaling("huidige_wedstrijd_stapel") + " ");
                List<String> wedstrijdStapel = dc.geefWedstrijdStapel(spelerAanBeurt);
                if (wedstrijdStapel.size() > 0) {
                    for (int i = 0; i < wedstrijdStapel.size(); i++) {
                        System.out.printf("%d) %s%n", i + 1, wedstrijdStapel.get(i));
                    }
                } else {
                    System.out.println(dc.getTaal().getVertaling("geen_wedstrijdstapem_uc6"));

                }

                System.out.println(dc.getTaal().getVertaling("kaart_opleggen") + ": ");

                while (kaartKeuze < 0 || kaartKeuze >= wedstrijdStapel.size()) {
                    boolean succes = false;
                    System.out.println(dc.getTaal().getVertaling("keuze_kaart_opleggen"));
                    System.out.print(" > ");
                    while (!succes) {
                        try {
                            kaartKeuze = input.nextInt() - 1;
                            succes = true;
                        } catch (InputMismatchException ex) {
                            System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + ex.getMessage());
                            System.out.print(" > ");
                            input.nextLine();
                        }
                    }
                }

                int keuzeType = 3;
                //geefKaartType klopt niet
                if (dc.geefKaartType(wedstrijdStapel.get(kaartKeuze)).equals("+/-")) {
                    boolean succes = false;
                    System.out.println(dc.getTaal().getVertaling("kaarttype_gebruiken"));
                    while (!succes) {
                        do {
                            System.out.println(dc.getTaal().getVertaling("keuze_kaart_opleggen"));
                            System.out.println("1. + ");
                            System.out.println("2. - ");
                            try {

                                keuzeType = input.nextInt();
                                succes = true;
                            } catch (InputMismatchException ex) {
                                System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + ex.getMessage());
                                System.out.print(" > ");
                                input.nextInt();
                            }
                        } while (keuzeType < 0 || keuzeType > 2);

                    }
                }
                //legWedstrijdkaart herbekijken
                dc.legWedstrijdkaart(wedstrijdStapel.get(kaartKeuze), keuzeType);
                System.out.println(dc.getTaal().getVertaling("kaart_toegevoegd_aan_spelbord"));

                for (int i = 0; i < spelers.size(); i++) {
                    System.out.println("------------------------");
                    System.out.println("------------------------");
                    if (dc.geefSpelbord(spelers.get(i)).isEmpty()) {
                        System.out.printf("%n" + " %s%n" + dc.getTaal().getVertaling("spelbord"), spelers.get(i));
                    } else {
                        System.out.printf(dc.getTaal().getVertaling("spelbord_not_empty") + spelers.get(i));

                        List<String> spelbord = dc.geefSpelbord(spelers.get(i));

                        for (int k = 0; k < spelbord.size(); k++) {
                            System.out.println(k + 1 + ") " + spelbord.get(k));
                        }

//                    for (int k = 0; k < dc.geefSpelbord(spelers.get(i)).size(); k++) {
//                        System.out.printf("%d) %s%n", k + 1, dc.geefSpelbord(spelers.get(i)));
//                        //wordt nog niet goed getoond
//                        //ik denk dat forlus weg mag
//                    }
                    }

                    System.out.printf("----------------%n");
                    System.out.printf(dc.getTaal().getVertaling("setscore") + " %s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                    System.out.printf(dc.getTaal().getVertaling("wedstrijd_stapel") + " %s%n", spelers.get(i));

                    for (int k = 0; k < wedstrijdStapel.size(); k++) {
                        System.out.println(k + 1 + ") " + wedstrijdStapel.get(k));
                    }

                    System.out.println("");
                    System.out.println("");

                }

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
        if ((speler1ScoreSetNaSpel - speler1ScoreSetVoorSpel) == (speler2ScoreSetNaSpel - speler2ScoreSetVoorSpel)) {
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
