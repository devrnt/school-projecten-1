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
        //spelerlijst lokaal bijhouden en spelers die al geregistreerd zijn verwijderen eruit
        Scanner input = new Scanner(System.in);
        dc.maakWedstrijd();
        String keuze = "";
        List<String> spelers = new ArrayList<>();
        while (spelers.size() < 2) {
            System.out.println(dc.getTaal().getVertaling("beschikbare_spelers"));
            dc.geefLijstBeschikbareSpelers().forEach((naam)->{ System.out.println(naam); });
            System.out.println(dc.getTaal().getVertaling("selecteer_speler"));
            keuze = input.nextLine();
            
            dc.registreerSpeler(keuze);
            //System.out.println(dc.getTaal().getVertaling("geregistreerde_spelers"));
            //spelers.forEach((naam)->{ System.out.println(naam); });
        }
        System.out.println(dc.getTaal().getVertaling("geregistreerde_spelers"));
        dc.geefGeregistreerdeSpelers().forEach((naam)->{ System.out.println(naam); });
            
        System.out.println(dc.getTaal().getVertaling("geen_wedstrijdstapel"));
        if(!dc.geefSpelerZonderWedstrijdStapel().isEmpty()){
            dc.geefSpelerZonderWedstrijdStapel().forEach((naam)->{ System.out.println(naam); });
        }
        //toDo geefActieveSpeler(deze wordt nog nergens geset)
    }
}
