/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;

/**
 *
 * @author Edward
 */
public class UC1 {
    public static void testUC1(DomeinController dc){
        //verkeerde gebruikersnamen
        //dc.maakSpeler("Te", 1990);
        //dc.maakSpeler("Te st", 1990);
        //dc.maakSpeler("5Test", 1990);
        //dc.maakSpeler("Test&", 1990);
        
        //verkeerde geboortedatum
        //dc.maakSpeler("Test", 1000);
        //dc.maakSpeler("Test", 2020);
        
        //correct aanmaken van de speler
        dc.maakSpeler("CorrecteTest150", 1990);
        
        //Methodes om de spelers op te vragen uit de database maken geen deel uit van UC1
    }
}
