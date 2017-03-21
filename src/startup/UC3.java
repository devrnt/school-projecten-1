/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import resources.Taal;

/**
 *
 * @author Edward
 */
public class UC3 {
    public static void testUC3(DomeinController dc) {
        Scanner input = new Scanner(System.in);
        dc.maakWedstrijd();
        String keuze = "";
        List<String> spelers = new ArrayList<>();
        while (spelers.size() < 2) {
            System.out.println("Dit zijn de beschikbare spelers: ");
            dc.geefLijstBeschikbareSpelers().forEach((naam)->{ System.out.println(naam); });
            System.out.println("Gelieve een van deze personen te selecteren");
            keuze = input.nextLine();
            
            spelers = dc.registreerSpeler(keuze);
            System.out.println("De volgende spelers zijn nu geregistreerd: ");
            spelers.forEach((naam)->{ System.out.println(naam); });
        }
        System.out.println("Deze spelers hebben nog geen wedstrijdStapel!");
        if(!dc.geefSpelerZonderWedstrijdStapel().isEmpty()){
            dc.geefSpelerZonderWedstrijdStapel().forEach((naam)->{ System.out.println(naam); });
        }
        //toDo geefActieveSpeler(deze wordt nog nergens geset)
    }
}
