/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.InputMismatchException;
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
        
        //de taal tijdelijk op nl zetten
        //dc.setTaal(new Taal("en"));
        // Scanner inputUser = new Scanner(System.in);
        // System.out.println(dc.getTaal().getVertaling("aantal_spelers"));
        // aantalGewensteSpelers = inputUser.nextInt();
        //boolean flag = true;
        boolean extraSpeler = true;

        while (extraSpeler) {
           
            try {
                Scanner input = new Scanner(System.in);
                String naam = "";
                int geboortejaar;

                System.out.println(dc.getTaal().getVertaling("naam_input"));
                naam = input.nextLine();
                System.out.println(dc.getTaal().getVertaling("geboortejaar_input"));
                geboortejaar = input.nextInt();

                dc.maakSpeler(naam, geboortejaar);

                try{
                
                System.out.println(dc.getTaal().getVertaling("extra_speler"));
                extraSpeler = input.nextBoolean();
                System.out.println("--------------------");
                
                } catch(InputMismatchException e){
                    System.err.println(dc.getTaal().getVertaling("antwoord_extra_speler"));
                }
                

            } catch (IllegalArgumentException e) {
                System.err.println(dc.getTaal().getVertaling(e.getMessage()));
            }

        }
    }
}
