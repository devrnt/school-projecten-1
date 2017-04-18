package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC7 {

    public static void testUC7(DomeinController dc, String speler) {
        List<String> startstapel = dc.toonStartStapel(speler);
        boolean keuze = true;
        Scanner input = new Scanner(System.in);

        do {

            System.out.printf("Huidig krediet: %f%n", dc.geefKredietSpeler(speler));
            System.out.printf("Aan te kopen kaarten: %n");
            for (int i = 0; i < i ; i++  ) {
                
            }
        } while (keuze);

    }
}
