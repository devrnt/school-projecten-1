    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Calendar;

/**
 *
 * @author Juan Carlos
 */
public class DomeinController {
    
    
    
    
    
     //later doen met reguliere expresies.
    public void controleerGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("Je voornaam moet minstens uit 3 karakters bestaan.");
        }
        if (gebruikersnaam.contains(" ")) {
            throw new IllegalArgumentException("Uw voornaam mag geen spaties bevatten.");
        }

        //Controle maken voor leestekes in naam
        //if(voornaam.matches("\\p{Punct}")){
        //    throw new IllegalArgumentException("Uw voornaam mag geen leestekens bevatten.");
        //}
        //alleen nodig bij voornaam
        if (Character.isDigit(gebruikersnaam.charAt(0))) {
            throw new IllegalArgumentException("Uw voornaam mag niet beginnen met een cijfer");
        }
        
    }


    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = Calendar.getInstance().get(Calendar.YEAR) - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("U moet minstens 6 jaar zijn.");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("U moet jonger zijn dan 99 jaar.");
        }
    }

    
}
