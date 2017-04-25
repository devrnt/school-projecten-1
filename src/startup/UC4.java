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
        List<String> startstapel = dc.toonStartStapel(speler);
        
        System.out.println("Geef telkens de index van de kaart die u wenst toe te voegen aan uw deck. (Uw selectie moet bestaan uit 6 kaarten)");
        while (selectie.size() < 6) {
            System.out.println("Geef een index van een kaart");
            int keuze = -1;
            boolean koopKaart;
            
            for (int i = 0; i < startstapel.size(); i++) {
                System.out.println(i + 1 + ") " + startstapel.get(i));
            }

                System.out.println("Wilt u een kaart kopen? true/false");
                koopKaart = input.nextBoolean();

            if (koopKaart) {
                List<String> gekochteKaarten = UC7.testUC7(dc, speler);
                for(String gekochteKaart : gekochteKaarten){
                    startstapel.add(gekochteKaart);
                }
            } else {
                while (keuze < 0 || keuze >= startstapel.size()) {
                    System.out.printf("Maak uw keuze%n");
                    System.out.print(" >");
                    keuze = input.nextInt() - 1;
                }
                selectie.add(startstapel.get(keuze));
                startstapel.remove(keuze);
            }
        }

        dc.maakWedstrijdStapel(speler, selectie);
    }
}





//VRAAG AAN LEERKRACHT: Controle bij boolean ingave, mss met inten of strings?
//                      Die loop bij uc4 van 3 -> 1 -> 3 oe daje da dan weet, zorgen dat die die al is gekocht zijn niet meer voorkomen
//                      In deze use case, intialiseerd ge voor uw FOR maar als ge dan een kaart koopt komt hij er niet bij omdat de startstapel niet wordt upgedate als je in de for loop zit in deze uc