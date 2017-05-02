package startup;

import domein.DomeinController;

/**
 *
 * @author Jonas
 */
public class UC5 {
    
        public static void testUC5(DomeinController dc) {
              
        dc.maakWedstrijd();
        int aantalGewonnenSetsSpeler1 = 0;
        int aantalGewonnenSetsSpeler2 = 0;
        
//         if(aantalGewonnenSetsSpeler1 < 3 && aantalGewonnenSetsSpeler2 < 3){
//             UC6.testUC6(dc);
//             if(?){
//                 aantalGewonnenSetsSpeler1++;
//             }else
//                 aantalGewonnenSetsSpeler2++;                     
//         }
         
        dc.verhoogKrediet();
        
        System.out.printf(dc.getTaal().getVertaling("winnaar") + "%s" + dc.getTaal().getVertaling("krediet") + "%d", dc.geefNaamWinnaar() , dc.geefKredietWinnaar());      
    }    
}
    

