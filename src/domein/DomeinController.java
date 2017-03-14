/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import Exceptions.GebruikernaamInGebruikException;
import java.util.Calendar;
import resources.Taal;

/**
 *
 * @author Juan Carlos
 */
public class DomeinController {

    private SpelerRepository spelerRepository;
    private KaartRepository kaartRepository;
    private Taal taal;

    public Taal getTaal() {
        return taal;
    }

    public void setTaal(Taal taal) {
        this.taal = taal;
    }

    public DomeinController() {
        spelerRepository = new SpelerRepository();  //het aanmaken van de repositorys gebeurt hier zodat ze niet elke keer opnieuw aangemaakt moeten worden
        kaartRepository = new KaartRepository();
    }

    public void maakSpeler(String naam, int geboortejaar) {        
        Speler nieuweSpeler = new Speler(naam, geboortejaar, 0);
        
        //validatie gebruikersnaam
        try{
            nieuweSpeler.controleerGebruikersnaam(naam);
            nieuweSpeler.controleerLeeftijd(geboortejaar);
        }catch(IllegalArgumentException ex){
            ex.printStackTrace();
            return;
        }
        //stapel kaarten moet nog toegevoegd worden
        spelerRepository.voegSpelerToe(nieuweSpeler); //nog surrounden met try-catch
        //kaartRepository.voegStartkaartenToeAanSpeler(nieuweSpeler);
        
    }
}
