package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC7 {

    public static void testUC7(DomeinController dc, String speler) {
        List<String> startStapel = dc.toonStartStapel(speler);
        List<String> alleKaarten = dc.toonAlleKaarten();
        boolean flag = true;
        int keuze = 0;
        Scanner input = new Scanner(System.in);

        do {

            System.out.printf("Huidig krediet: %f%n", dc.geefKredietSpeler(speler));
            System.out.printf("Aan te kopen kaarten: %n");
            for (int i = 0; i < alleKaarten.size(); i++) {
                String[] parts = alleKaarten.get(i).split(" ");
                String omschrijving = parts[0];
                
                if (omschrijving != startStapel.get(i)) {
                    System.out.printf(i + 1 + ") " + alleKaarten.get(i));
                }
            }
            System.out.printf("Maak uw keuze.%n > ");
            keuze = input.nextInt() - 1;

            String[] parts = alleKaarten.get(keuze).split(" ");
            String omschrijving = parts[0];
            String prijsS = parts[1];
            
            int prijsI = Integer.parseInt(prijsS);
            
            startStapel.add(omschrijving);
        } while (flag);

    }
}
