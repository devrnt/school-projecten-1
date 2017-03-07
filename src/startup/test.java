/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Speler;
import domein.SpelerRepository;

/**
 *
 * @author Jonas
 */
public class test {

    public static void main(String[] args) {
        Speler speler = new Speler("Te", 1990, 5);
        System.out.println(speler.getGebruikersnaam());

        //testen database
        DomeinController domeinctrl = new DomeinController();
        //Testen speler toevoegen
        //verkeerde namen
        domeinctrl.maakSpeler("Te", 1990);
        domeinctrl.maakSpeler("Te st", 1990);
        domeinctrl.maakSpeler("5Test", 1990);
        
        //verkeerde geboortedatum
        domeinctrl.maakSpeler("Test", 1000);
        domeinctrl.maakSpeler("Test", 2020);
        
        //domeinctrl.maakSpeler("Test", 1990);  //algemene speler, moet slagen
        
    }
}
