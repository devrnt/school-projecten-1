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
    private WedstrijdRepository wedstrijdRepository;
    private Taal taal;
    private Wedstrijd wedstrijd;
    private Speler speler;
    private List<Kaart> alleKaarten;

    ;

    public Taal getTaal() {
        return taal;
    }

    public void setTaal(Taal taal) {
        this.taal = taal;
    }

    public List<Kaart> geefAlleKaarten() {
        return alleKaarten;
    }

    public DomeinController() {
        spelerRepository = new SpelerRepository();  //het aanmaken van de repositorys gebeurt hier zodat ze niet elke keer opnieuw aangemaakt moeten worden
        kaartRepository = new KaartRepository(); 
        wedstrijdRepository = new WedstrijdRepository();
        alleKaarten = kaartRepository.getKaarten();
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
    // lijst van Strings 
    public List<String> toonStartStapel(String naam) {
        // Speler ophalen uit wedstrijd
        Speler spelerL = wedstrijd.geefSpeler(naam);
        List<String> output = new ArrayList<>();
        for (Kaart kaart : spelerL.getKaartLijst()) {
            output.add(kaart.getOmschrijving());
        }

        return output;
    }

    public int geefAantalKaartenWedstrijdStapel() {
        return speler.getWedstrijdStapel().size();
    }

    public void maakWedstrijdStapel(String naam, List<String> stapel) {

        wedstrijd.maakWedstrijdStapel(naam, stapel);
    }

    public double geefKredietWinnaar() {
        return wedstrijd.getWinnaar().getKrediet();
    }

    public String geefNaamWinnaar() {
        return wedstrijd.getWinnaar().getGebruikersnaam();
    }

    public void verhoogKrediet() {
        wedstrijd.verhoogKrediet();
    }

    public void verminderKrediet(double prijs, String speler) {
        wedstrijd.verminderKrediet(prijs, speler);
    }

    public double geefKredietSpeler(String naam) {

        return wedstrijd.geefSpeler(naam).getKrediet();
    }

    public List<String> toonBetalendeKaarten() { //UC7
        List<String> output1 = new ArrayList<>();
        geefAlleKaarten().forEach((kaart) -> {
            if (kaart.getPrijs() != 0) {
                output1.add(kaart.getOmschrijving() + " " + kaart.getPrijs());
            }
        });
        return output1;
    }

    public void voegBetaaldeKaartToeAanStartStapel(String speler, String omschrijving) { //UC7

        for (Kaart kaart : geefAlleKaarten()) {
            if (omschrijving.equals(kaart.getOmschrijving())) {
                wedstrijd.voegBetaaldeKaartenToeAanStartStapel(speler, kaart);   
            }
        }
    }
    
    public void maakSetStapel() {
       wedstrijd.maakSetStapel();
    }
    
    public void bewaarWedstrijd(String naam){
        //opslaan van de wedstrijd
        wedstrijdRepository.bewaarWedstrijd(naam, wedstrijd);
        for(String gNaam: wedstrijd.geefGeregistreerdeSpelers()){
            spelerRepository.updateSpeler(wedstrijd.geefSpeler(gNaam));
        }
    }
    
    public List<String> toonLijstWedstrijden(){
        return wedstrijdRepository.toonLijstWedstrijden();
    }
    
    public void laadWedstrijd(String naam){
        wedstrijd = wedstrijdRepository.laadWedstrijd(naam);
    }
    
}