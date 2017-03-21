/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import resources.Taal;

/**
 *
 * @author Juan Carlos
 */
public class DomeinController {

    private SpelerRepository spelerRepository;
    private KaartRepository kaartRepository;
    private Taal taal;
    private Wedstrijd wedstrijd;

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
            throw ex;
        }
        
        nieuweSpeler.setStartStapel(kaartRepository.getStartKaarten());
        
        spelerRepository.voegSpelerToe(nieuweSpeler); //nog surrounden met try-catch
        kaartRepository.voegStartkaartenToeAanSpeler(nieuweSpeler);
    }
    
    public List<String> geefLijstBeschikbareSpelers(){
        return spelerRepository.geefLijstBeschikbareSpelers();
    }
    
    public void maakWedstrijd(){
        wedstrijd = new Wedstrijd();
    }
    
    public List<String> registreerSpeler(String gebruikersnaam){
        return wedstrijd.registreerSpeler(spelerRepository.geefSpeler(gebruikersnaam));
    }
    
    public List<String> geefSpelerZonderWedstrijdStapel(){
        return wedstrijd.geefSpelersZonderWedstrijdStapel();
    }
    /*public List<String> registreerSpeler(String gebruikersnaam){
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if(speler != null && geregistreerdeSpelers.size() < 2){
            geregistreerdeSpelers.add(speler);
        }
        return new ArrayList<>(Arrays.asList(geregistreerdeSpelers.get(0).getGebruikersnaam(), geregistreerdeSpelers.get(1).getGebruikersnaam()));
    }*/
    
}
