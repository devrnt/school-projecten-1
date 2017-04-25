package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UC4 {

    public static void testUC4(DomeinController dc, String speler) {

        Scanner input = new Scanner(System.in);

        // Dit moet gebeuren voor de beide speler
        List<String> selectie = new ArrayList<>();
        //startstapel.forEach((omschrijving)->{ System.out.println(omschrijving); });
        System.out.println("Geef telkens de index van de kaart die u wenst toe te voegen aan uw deck. (Uw selectie moet bestaan uit 6 kaarten)");
        while (selectie.size() < 6) {
            System.out.println("Geef een index van een kaart");
            int keuze = -1;
            List<String> startstapel = dc.toonStartStapel(speler);
            
            for (int i = 0; i < startstapel.size(); i++) {
                System.out.println(i + ") " + startstapel.get(i));
            }
            System.out.println("Wilt u een kaart kopen? true/false");
            boolean koopKaart = input.nextBoolean();
            if (koopKaart) {
                UC7.testUC7(dc, speler);
            } else {
                while (keuze < 0 || keuze >= startstapel.size()) {
                    System.out.printf("Maak uw keuze%n");
                    System.out.print(" >");
                    keuze = input.nextInt();
                }
                selectie.add(startstapel.get(keuze));
                startstapel.remove(keuze);
            }
        }

        dc.maakWedstrijdStapel(speler, selectie);
    }
}
