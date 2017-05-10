package startup;

import domein.DomeinController;
import java.util.List;
import java.text.MessageFormat;

/**
 *
 * @author Jonas
 */
public class UC5 {

    public static void testUC5(DomeinController dc) {
        
        List<String> spelersWedstrijd;
        
        do {
            UC6.testUC6(dc);
            spelersWedstrijd = dc.geefGeregistreerdeSpelers();
            // herschrijven
        } while (dc.geefSetScore(spelersWedstrijd.get(0)) < 3 || dc.geefSetScore(spelersWedstrijd.get(1)) < 3);

        dc.verhoogKrediet();

        System.out.println(MessageFormat.format(dc.getTaal().getVertaling("winnaar"), dc.geefNaamWinnaar(), dc.geefKredietWinnaar()));
        //System.out.printf(dc.getTaal().getVertaling("winnaar") + "%s" + dc.getTaal().getVertaling("krediet") + "%d", dc.geefNaamWinnaar(), dc.geefKredietWinnaar());
    }
}
