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
        dc.bepaalSpelerAanDeBeurtEersteSet();

        while (!dc.setEinde()) {
            //eigenlijk mag de dc.bepaalSpelerAanEersteBeurt weg !!

            //controle welke set ronde
            dc.voegBovensteKaartVanSetStapelToeAanSpelbord();

            List<String> spelers = dc.geefGeregistreerdeSpelers();

            for (int i = 0; i < spelers.size(); i++) {
                System.out.println("-----------------------------------");
                System.out.println("-----------------------------------");
                if (dc.geefSpelbord(spelers.get(i)).isEmpty()) {
                    System.out.printf("%n" + "%s " + dc.getTaal().getVertaling("spelbord") + "%n", spelers.get(i));
                } else {
                    System.out.printf(dc.getTaal().getVertaling("spelbord_not_empty") + " %s%n ", spelers.get(i));

                    List<String> spelbord = dc.geefSpelbord(spelers.get(i));

                    for (int k = 0; k < spelbord.size(); k++) {
                        System.out.println(k + 1 + ") " + spelbord.get(k));
                    }

                }

                System.out.printf("%n");
                System.out.printf(dc.getTaal().getVertaling("spelbord_score") + "%d%n", dc.geefSpelbordScore(spelers.get(i)));
                System.out.printf(dc.getTaal().getVertaling("setscore") + "%s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                System.out.printf(dc.getTaal().getVertaling("wedstrijd_stapel") + "%s%n", spelers.get(i));

                List<String> wedstrijdStapel = dc.geefWedstrijdStapel(spelers.get(i));

                for (int k = 0; k < wedstrijdStapel.size(); k++) {
                    System.out.println(k + 1 + ") " + wedstrijdStapel.get(k));
                }

                System.out.println("");

            }

            String spelerAanBeurt = dc.geefActieveSpeler();
            System.out.printf(dc.getTaal().getVertaling("actieve_speler") + "%s%n", spelerAanBeurt);
            System.out.printf("-----------------------------------%n");

            System.out.printf(dc.getTaal().getVertaling("speler_aan_beurt_keuze") + " %s?%n", spelerAanBeurt);

            Scanner input = new Scanner(System.in);
            int menuKeuze = 0;
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
                dc.beeindigBeurt();//werkt nog niet: het lukt niet om van actieve speler te switchen

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
                String kaartType = dc.geefKaartType(wedstrijdStapel.get(kaartKeuze));
                if (kaartType.equals("+/-")) {
                    System.out.println(dc.getTaal().getVertaling("kaarttype_gebruiken"));
                    while (keuzeType < 0 || keuzeType > 2) {
                        boolean succes = false;

                        while (!succes) {

                            System.out.println(dc.getTaal().getVertaling("keuze_kaart_opleggen"));
                            System.out.println("1) + ");
                            System.out.println("2) - ");

                            try {
                                System.out.print(" > ");

                                keuzeType = input.nextInt();
                                succes = true;
                            } catch (InputMismatchException ex) {
                                System.err.println(dc.getTaal().getVertaling("integer_input"));
                                System.out.print(" > ");
                                input.nextLine();
                            }
                        }
                    }
                }
                //legWedstrijdkaart herbekijken: alle kaarten worden gelegd niet de keuze
                dc.legWedstrijdkaart(dc.geefWedstrijdStapel(spelerAanBeurt).get(kaartKeuze), keuzeType);
                System.out.println(dc.getTaal().getVertaling("kaart_toegevoegd_aan_spelbord"));

                for (int i = 0; i < spelers.size(); i++) {
                    System.out.println("-----------------------------------");
                    System.out.println("-----------------------------------");
                    if (dc.geefSpelbord(spelers.get(i)).isEmpty()) {
                        System.out.printf("%n" + "%s " + dc.getTaal().getVertaling("spelbord") + "%n", spelers.get(i));
                    } else {
                        System.out.printf(dc.getTaal().getVertaling("spelbord_not_empty") + " %s%n ", spelers.get(i));

                        List<String> spelbord = dc.geefSpelbord(spelers.get(i));

                        for (int k = 0; k < spelbord.size(); k++) {
                            System.out.println(k + 1 + ") " + spelbord.get(k));
                        }

                    }

                    System.out.printf("%n");
                    System.out.printf(dc.getTaal().getVertaling("setscore") + "%s: %d%n", spelers.get(i), dc.geefSetScore(spelers.get(i)));

                    System.out.printf(dc.getTaal().getVertaling("wedstrijd_stapel") + "%s%n", spelers.get(i));

                    for (int k = 0; k < dc.geefWedstrijdStapel(spelers.get(i)).size(); k++) {
                        System.out.println(k + 1 + ") " + dc.geefWedstrijdStapel(spelers.get(i)).get(k));
                    }

                    System.out.println("");

                }
                dc.bepaalSpelerAanVolgendeBeurt();
            }

            if (menuKeuze == 3) {
                dc.bevriesSpelbord();
            }
        }

        if (dc.setEinde()) {
            dc.geefUitslag();

            if (dc.geefNaamSetWinnaar() == null) {
                System.out.println(dc.getTaal().getVertaling("set_gelijkspel"));
            } else {
                System.out.printf(dc.getTaal().getVertaling("set_winnaar") + " %s " + dc.getTaal().getVertaling("set_winnaar_score") + " %d%n%n", dc.geefNaamSetWinnaar(), dc.geefSetScore(dc.geefNaamSetWinnaar()));

            }
            dc.resetSet();
        }

    }

}
