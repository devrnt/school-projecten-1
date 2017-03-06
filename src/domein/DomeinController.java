/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Exceptions.GebruikernaamInGebruikException;
import java.util.Calendar;

/**
 *
 * @author Juan Carlos
 */
public class DomeinController {

    private SpelerRepository spelerRepository;
    private KaartRepository kaartRepository;

    public DomeinController() {
        spelerRepository = new SpelerRepository();  //het aanmaken van de repositorys gebeurt hier zodat ze niet elke keer opnieuw aangemaakt moeten worden
        kaartRepository = new KaartRepository();
    }

    public void maakSpeler(String naam, int geboortejaar) {
        try {
            controleerGebruikersnaam(naam);
            controleerLeeftijd(geboortejaar);
        }catch(IllegalArgumentException e){ //kan ook custom exceptions gebruiken, of exception die erven van IlligalArgumentException
            System.out.println(e.getMessage());
            return; //stopt de methode, de speler wordt niet aangemaakt
        }
        Speler nieuweSpeler = new Speler(naam, geboortejaar, 0);
        //stapel kaarten moet nog toegevoegd worden
        try{
        spelerRepository.voegSpelerToe(nieuweSpeler); //nog surrounden met try-catch
        }catch(GebruikernaamInGebruikException gige){
            System.out.println(gige.getMessage());  //veranderen door een boodschap op de ui
        }
    }

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
