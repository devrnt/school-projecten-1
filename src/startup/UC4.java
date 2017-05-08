package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UC4 {

    public static void testUC4(DomeinController dc, String speler) {

        Scanner input = new Scanner(System.in);

        // Dit moet gebeuren voor de beide speler
        List<String> selectie = new ArrayList<>();

        System.out.println(dc.getTaal().getVertaling("uitleg_selectie"));
        while (selectie.size() < 6) {

            List<String> startstapel = dc.toonStartStapel(speler);
            for (String selectieKaart : selectie) {
                for (int i = 0; i < startstapel.size(); i++) {
                    if (selectieKaart.equals(startstapel.get(i))) {
                        startstapel.remove(i);
                    }
                }
            }
            int keuze = -1;
            int wilKaartKopen = 0;

            for (int i = 0; i < startstapel.size(); i++) {
                System.out.println(i + 1 + ") " + startstapel.get(i));
            }

            System.out.println(dc.getTaal().getVertaling("kaart_kopen"));
            System.out.println("1. " + dc.getTaal().getVertaling("ja"));
            System.out.println("2. " + dc.getTaal().getVertaling("nee"));
            System.out.print(" > ");

            while (wilKaartKopen < 1 || wilKaartKopen > 2) {
                boolean done = false;
                while (!done) {
                    try {
                        wilKaartKopen = input.nextInt(); //INPUTMISMATCH MOGELIJKHEID
                        done = true;
                    } catch (InputMismatchException e) {
                        System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + e.getMessage());
                        System.out.print(" > ");
                        input.nextLine();
                    }
                }
            }

            if (wilKaartKopen == 1) {
                UC7.testUC7(dc, speler);

            } else {
                System.out.println(dc.getTaal().getVertaling("geef_index"));
                System.out.print(" > ");

                while (keuze < 0 || keuze >= startstapel.size()) {
                    boolean done = false;
                    while (!done) {
                        try {
                            keuze = input.nextInt() - 1;
                            done = true;
                        } catch (InputMismatchException e) {
                            System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + e.getMessage());
                            System.out.print(" > ");
                            input.nextLine();
                        }
                    }
                }
                selectie.add(startstapel.get(keuze));
            }
        }
        dc.maakWedstrijdStapel(speler, selectie);
    }
}

//VRAAG AAN LEERKRACHT: Controle bij boolean ingave, mss met inten of strings?
//                      Die loop bij uc4 van 3 -> 1 -> 3 oe daje da dan weet, zorgen dat die die al is gekocht zijn niet meer voorkomen
//                      In deze use case, intialiseerd ge voor uw FOR maar als ge dan een kaart koopt komt hij er niet bij omdat de startstapel niet wordt upgedate als je in de for loop zit in deze uc
