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
public class UC2 {

    public static void testUC2(DomeinController dc) {
        Scanner input = new Scanner(System.in);
        String keuze = "";
        while (!keuze.equals("nl") && !keuze.equals("fr") && !keuze.equals("en")) {
            System.out.println("Voor Nederlands: typ nl");
            System.out.println("Pour Français: choisissez fr");
            System.out.println("For English: choose en");
            keuze = input.nextLine();
        }
        if (keuze.equals("")) {
            keuze = "en";
        } //standaard setting (kan ook gedaan worden door resource_en te hernoemen naar resource)
        dc.setTaal(new Taal(keuze));
<<<<<<< Upstream, based on origin/master
        System.out.println(dc.getTaal().getVertaling("wedstrijd_start"));
        System.out.println(dc.getTaal().getVertaling("maak_speler"));
        System.out.println(dc.getTaal().getVertaling("laad_wedstrijd"));
=======
        
        int i = 1;
        System.out.printf("%d. %s%n",i, dc.getTaal().getVertaling("wedstrijd_start"));
        i++;
        System.out.printf("%d. %s%n",i, dc.getTaal().getVertaling("maak_speler")); 
        i++;
        System.out.printf("%d. %s%n",i, dc.getTaal().getVertaling("laad_wedstrijd"));
        i=0;
        
>>>>>>> 7033385 UC1 afgewerkt + taal prop
    }
}
