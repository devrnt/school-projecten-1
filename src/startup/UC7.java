package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC7 {

    public static List<String> testUC7(DomeinController dc, String speler) {
        Scanner input = new Scanner(System.in);

        List<String> gekochteKaarten = new ArrayList<>();
        List<String> startStapel = dc.toonStartStapel(speler);
        List<String> betalendeKaarten = dc.toonBetalendeKaarten();

        boolean flag;

        do {
            System.out.printf("Huidig krediet: %.1f%n", dc.geefKredietSpeler(speler));
            System.out.printf("Aan te kopen kaarten: %n");
            for (int i = 0; i < betalendeKaarten.size() - 1; i++) {
                String[] parts = betalendeKaarten.get(i).split(" ");
                String omschrijving = parts[0];
                String prijsS = parts[1];

                System.out.printf(i + 1 + ") " + omschrijving + "\t \t €" + prijsS + "%n");
            }
            int keuze = -1;

            do {

                System.out.printf("Maak uw keuze.%n > ");
                keuze = input.nextInt() - 1;

            } while (keuze < 0 || keuze > betalendeKaarten.size());

            String[] parts = betalendeKaarten.get(keuze).split(" ");
            String omschrijving = parts[0];
            String prijsS = parts[1];
            double prijsD = Double.parseDouble(prijsS);
            if (dc.geefKredietSpeler(speler) >= prijsD ) {
                
                gekochteKaarten.add(omschrijving);
                dc.verminderKrediet(prijsD, speler);
            } else {
                
                System.out.println("U heeft niet genoeg krediet om de kaart te kopen");
                System.out.printf("Huidig krediet: %.1f%n", dc.geefKredietSpeler(speler));
            }

                System.out.println("Wilt u nog een kaart te kopen? true/false ");
                System.out.printf(" > ");
                flag = input.nextBoolean();

        } while (flag);
// KAART MOET MAG NIET MEER GETOOND WORDEN ALS JE DOOR DE LOOP GAAT VOOR DE TWEEDE KEER
        System.out.println("Deze kaarten worden toegevoegd aan uw startstapel");
        gekochteKaarten.forEach((kaart) -> {
            System.out.printf(" - %s%n", kaart);
        });
        
        dc.voegBetaaldeKaartenToe(speler, gekochteKaarten);

        return gekochteKaarten;
    }
}
