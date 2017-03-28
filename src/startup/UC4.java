package startup;

import domein.DomeinController;
import domein.Speler;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC4 {

    public static void testUC4(DomeinController dc, String speler) {

        Scanner input = new Scanner(System.in);

        
        // Dit moet gebeuren voor de beide speler
        
        List<String> resultaat = new ArrayList<String>();
        List<String> startstapel = dc.toonNGKaartenStartStapel();

        while (resultaat.size() < 6) {
            System.out.println("Geef een nummer van een kaart");
            int keuze = -1;
            while (keuze < 0 || keuze >= startstapel.size()) {
                for (int i = 0; i < startstapel.size(); i++) {
                    System.out.println(i + " " + startstapel.get(i));
                }
                keuze = input.nextInt();
            }
            resultaat.add(startstapel.get(keuze));
            startstapel.remove(keuze);
        }

        dc.maakWedstrijdStapel(naam_van_de_speler, resultaat);
    }
}
