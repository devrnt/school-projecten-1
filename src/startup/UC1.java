/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.Scanner;
import resources.Taal;

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
        int aantalSpelers = 0;
        int aantalGewensteSpelers;

        //de taal tijdelijk op nl zetten
        //dc.setTaal(new Taal("en"));
        Scanner inputUser = new Scanner(System.in);

        System.out.println(dc.getTaal().getVertaling("aantal_spelers"));
        aantalGewensteSpelers = inputUser.nextInt();

        boolean flag = true;

        while (aantalSpelers < aantalGewensteSpelers) {

            do {

                try {
                    Scanner input = new Scanner(System.in);
                    String naam = "";
                    int geboortejaar;

                    System.out.println(dc.getTaal().getVertaling("naam_input"));
                    naam = input.nextLine();
                    System.out.println(dc.getTaal().getVertaling("geboortejaar_input"));
                    geboortejaar = input.nextInt();

                    dc.maakSpeler(naam, geboortejaar);
                    aantalSpelers++;

                } catch (IllegalArgumentException e) {
                    System.out.println(dc.getTaal().getVertaling(e.getMessage()));
                }

            } while (flag); //while (aantalSpelers < aantalGewensteSpelers);
            //Methodes om de spelers op te vragen uit de database maken geen deel uit van UC1
        }
    }
}
