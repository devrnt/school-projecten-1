package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC7 {

    public static void testUC7(DomeinController dc, String speler) {
        Scanner input = new Scanner(System.in);

        List<String> gekochteKaarten = new ArrayList<>();
        List<String> startStapel = dc.toonStartStapel(speler);
        List<String> alleKaarten = dc.toonAlleKaarten();

        boolean flag;

        do {
            System.out.printf("Huidig krediet: %.1f%n", dc.geefKredietSpeler(speler));
            System.out.printf("Aan te kopen kaarten: %n");
            for (int i = 0; i < alleKaarten.size() - 1; i++) {
                String[] parts = alleKaarten.get(i).split(" ");
                String omschrijving = parts[0];
                String prijsS = parts[1];
                double prijsD = Double.parseDouble(prijsS);
                
                if (prijsD == 0) {
                } else if (omschrijving.equals(startStapel.get(i))) {
                } else {
                    System.out.printf(i + 1 + ") " + omschrijving + " €" + prijsS + "%n");
                }
            }
            System.out.printf("Maak uw keuze.%n > ");
            int keuze = input.nextInt() - 1;

            String[] parts = alleKaarten.get(keuze).split(" ");
            String omschrijving = parts[0];
            String prijsS = parts[1];
            double prijsD = Double.parseDouble(prijsS);

            gekochteKaarten.add(omschrijving);
            dc.verminderKrediet(prijsD, speler);

            System.out.println("Wenst u nog een kaart te kopen? true/false ");
            System.out.printf(" > ");
            flag = input.nextBoolean();

        } while (flag);
// KAART MOET MAG NIET MEER GETOOND WORDEN ALS JE DOOR DE LOOP GAAT VOOR DE TWEEDE KEER
        System.out.println("Deze kaarten worden toegevoegd aan uw startstapel");
        gekochteKaarten.forEach((kaart) -> {
            System.out.printf(" - %s%n", kaart);
        });
        dc.setStartStapel(gekochteKaarten, speler);

    }
}
