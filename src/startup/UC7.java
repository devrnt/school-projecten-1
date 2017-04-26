package startup;

import domein.DomeinController;
import java.util.ArrayList;
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
            System.out.printf("Huidig krediet: %.1f%n", dc.geefKredietSpeler(speler));
            System.out.printf("Aan te kopen kaarten: %n");
            for (int i = 0; i < betalendeKaarten.size(); i++) {
                String[] parts = betalendeKaarten.get(i).split(" ");
                String omschrijving = parts[0];
                String prijsS = parts[1];

                System.out.printf(i + 1 + ") " + omschrijving + " €" + prijsS + "%n");
            }
            int keuze = -1;

            while (keuze < 0 || keuze > betalendeKaarten.size()) {

                System.out.printf("Maak uw keuze.%n > ");
                keuze = input.nextInt() - 1;

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

                System.out.println("U heeft niet genoeg krediet om de kaart te kopen");
                System.out.printf("Huidig krediet: %.1f%n", dc.geefKredietSpeler(speler));
            }

            do {
                System.out.println("Wilt u nog een kaart te kopen?");
                System.out.println("1. Ja");
                System.out.println("2. Nee");
                nogEenKaartKopen = input.nextInt();
            } while (nogEenKaartKopen < 1 || nogEenKaartKopen > 2);
        }
    }
}
