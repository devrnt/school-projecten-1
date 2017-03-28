/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
    private Speler speler;

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
        try {
            nieuweSpeler.controleerGebruikersnaam(naam);
            nieuweSpeler.controleerLeeftijd(geboortejaar);
        } catch (IllegalArgumentException ex) {
            throw ex;
        }

        nieuweSpeler.setStartStapel(kaartRepository.getStartKaarten());

        spelerRepository.voegSpelerToe(nieuweSpeler); //nog surrounden met try-catch
        kaartRepository.voegStartkaartenToeAanSpeler(nieuweSpeler);
    }

    public List<String> geefLijstBeschikbareSpelers() {
        return spelerRepository.geefLijstBeschikbareSpelers();
    }

    public void maakWedstrijd() {
        wedstrijd = new Wedstrijd();
    }

    public void registreerSpeler(String gebruikersnaam) {
        wedstrijd.registreerSpeler(spelerRepository.geefSpeler(gebruikersnaam));
    }

    public List<String> geefGeregistreerdeSpelers() {
        return wedstrijd.geefGeregistreerdeSpelers();
    }

    public List<String> geefSpelerZonderWedstrijdStapel() {
        return wedstrijd.geefSpelersZonderWedstrijdStapel();
    }

    /*public List<String> registreerSpeler(String gebruikersnaam){
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if(speler != null && geregistreerdeSpelers.size() < 2){
            geregistreerdeSpelers.add(speler);
        }
        return new ArrayList<>(Arrays.asList(geregistreerdeSpelers.get(0).getGebruikersnaam(), geregistreerdeSpelers.get(1).getGebruikersnaam()));
    }*/
    public void selecteerKaartVoorWedstrijdStapel(int keuze) {
        //toDo kaart uit de startstapel toevoegen aan wedstrijdstapel
        switch (keuze) {
        }

    }

    public String toonNGKaartenStartStapel() {
        String output = "";
        int teller = 0;

        for (Kaart kaart : speler.toonNietGeselecteerdeKaarten()) {
            teller++;
            output += String.format("%d. %s", teller, kaart.getOmschrijving());
        }

        return output;
    }

    public int geefAantalKaartenWedstrijdStapel() {
        int aantal = 0;

        for (Kaart kaart : speler.getWedstrijdStapel()) {
            aantal++;
        }

        return aantal;
    }

    public void maakWedstrijdStapel() {
        int min = 0;
        int max = 5;
        Random rand = null;
        
        do {

            int randomNummer = rand.nextInt((max - min) + 1) + min;

        } while (speler.getAantalKaartenInWedstrijdStapel() > 4);

    }
}
