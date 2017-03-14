/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class UC1 {

    public static void testUC1(DomeinController dc) {
        //verkeerde gebruikersnamen
        //dc.maakSpeler("Te", 1990);
        //dc.maakSpeler("Te st", 1990);
        //dc.maakSpeler("5Test", 1990);
        //dc.maakSpeler("Test&", 1990);

        //verkeerde geboortedatum
        //dc.maakSpeler("Test", 1000);
        //dc.maakSpeler("Test", 2020);
        //correct aanmaken van de speler
  
            Scanner input = new Scanner(System.in);
            String naam = "";
            int geboortejaar;
            

            System.out.println(dc.getTaal().getVertaling("naam_input"));
            naam = input.nextLine();
            System.out.println(dc.getTaal().getVertaling("geboortejaar_input"));
            geboortejaar = input.nextInt();
            
            try {
                dc.maakSpeler(naam, geboortejaar);
    
            } catch (IllegalArgumentException e) {
                System.out.println(dc.getTaal().getVertaling(e.getMessage()));
            }
           

        

        //Methodes om de spelers op te vragen uit de database maken geen deel uit van UC1
    }
}

