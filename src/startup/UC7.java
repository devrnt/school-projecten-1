package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class UC7 {

    public static void testUC7(DomeinController dc, String speler) {
        Scanner input = new Scanner(System.in);

        List<String> startStapel = dc.toonStartStapel(speler);
        List<String> betalendeKaarten = dc.toonBetalendeKaarten();

        for (int i = 0; i < betalendeKaarten.size(); i++) {
            String[] parts = betalendeKaarten.get(i).split(" ");
            String omschrijving = parts[0];
            for (String startStapelKaart : startStapel) {
                if (startStapelKaart.equals(omschrijving)) {
                    betalendeKaarten.remove(i);
                }
            }
        }
        int nogEenKaartKopen = 1;

        while (nogEenKaartKopen == 1) {
            System.out.printf(dc.getTaal().getVertaling("huidig_krediet") + "%.1f%n", dc.geefKredietSpeler(speler));
            System.out.println(dc.getTaal().getVertaling("kaarten_tekoop"));
            for (int i = 0; i < betalendeKaarten.size(); i++) {
                String[] parts = betalendeKaarten.get(i).split(" ");
                String omschrijving = parts[0];
                String prijsS = parts[1];

                System.out.printf(i + 1 + ") " + omschrijving + " €" + prijsS + "%n");
            }

            int keuze = -1;
            System.out.printf(dc.getTaal().getVertaling("geef_index") + ".%n > ");
            while (keuze < 0 || keuze > betalendeKaarten.size()) {
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

            String[] parts = betalendeKaarten.get(keuze).split(" ");
            String omschrijving = parts[0];
            String prijsS = parts[1];
            double prijsD = Double.parseDouble(prijsS);
            if (dc.geefKredietSpeler(speler) >= prijsD) {

                dc.voegBetaaldeKaartToeAanStartStapel(speler, omschrijving);
                betalendeKaarten.remove(keuze);
                dc.verminderKrediet(prijsD, speler);
            } else {

                System.out.println(dc.getTaal().getVertaling("nietgenoeg_krediet"));
                System.out.printf(dc.getTaal().getVertaling("huidig_krediet") + "%.1f%n", dc.geefKredietSpeler(speler));
            }
            
            System.out.println("Ok!");
            System.out.println();
            System.out.println(dc.getTaal().getVertaling("nog_aankopen"));
            System.out.println("1. " + dc.getTaal().getVertaling("ja"));
            System.out.println("2. " + dc.getTaal().getVertaling("nee"));
            System.out.print(" > ");

            do {
                boolean done = false;
                while (!done) {
                    try {
                        nogEenKaartKopen = input.nextInt();
                        done = true;
                    } catch (InputMismatchException e) {
                        System.err.println(dc.getTaal().getVertaling("integer_input") + " Error: " + e.getMessage());
                        System.out.print(" > ");
                        input.nextLine();
                    }
                }
            } while (nogEenKaartKopen < 1 || nogEenKaartKopen > 2);
        }
    }
}
