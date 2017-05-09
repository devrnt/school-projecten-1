/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
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

    /**
     * Geeft de Taal van de applicatie
     * @return Taal van de applicatie
     */
    public Taal getTaal() {
        return taal;
    }

    /**
     * Setter van de taal van de applicatie
     * @param taal taal van de applicatie
     */
    public void setTaal(Taal taal) {
        this.taal = taal;
    }

    /**
     * Geeft een lijst van alle kaarten
     * @return lijst van alle kaarten
     */
    public List<Kaart> geefAlleKaarten() {
        return alleKaarten;
    }

    /**
     * Default constructor
     */
    public DomeinController() {
        spelerRepository = new SpelerRepository();  //het aanmaken van de repositorys gebeurt hier zodat ze niet elke keer opnieuw aangemaakt moeten worden
        kaartRepository = new KaartRepository(); 
        wedstrijdRepository = new WedstrijdRepository();
        alleKaarten = kaartRepository.getKaarten();
    }

    /**
     * Maakt een Nieuwe Speler met opgegeven naam en geboortejaar
     * @param naam naam van de speler
     * @param geboortejaar geboortejaar van de speler
     * @exception IllegalArgumentException Wanneer er geen legale naam of geboortejaar is opgegeven
     */
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

    /**
     * Geeft de lijst van de beschikbare spelers
     * @return lijst van beschikbare spelers
     */
    public List<String> geefLijstBeschikbareSpelers() {
        return spelerRepository.geefLijstBeschikbareSpelers();
    }

    /**
     * Maakt een nieuwe wedstrijd
     */
    public void maakWedstrijd() {
        wedstrijd = new Wedstrijd();
    }

    /**
     * Registreert een speler aan de hand van gebruikersnaam
     * @param gebruikersnaam gebruikersnaam van de speler
     */
    public void registreerSpeler(String gebruikersnaam) {
        wedstrijd.registreerSpeler(spelerRepository.geefSpeler(gebruikersnaam));
    }

    /**
     * Geeft een lijst van alle geregistreerde spelers
     * @return lijst van alle geregistreerde spelers
     */
    public List<String> geefGeregistreerdeSpelers() {
        return wedstrijd.geefGeregistreerdeSpelers();
    }

    /**
     * Geeft een lijst van spelers zonder wedstrijdstapel
     * @return lijst van spelers zonder wedstrijdstapel
     */
    public List<String> geefSpelerZonderWedstrijdStapel() {
        return wedstrijd.geefSpelersZonderWedstrijdStapel();
    }

    public List<String> geefWedstrijdStapel(String naam){
        List <Kaart> wedstrijdStapel = wedstrijd.geefSpeler(naam).getWedstrijdStapel();
        List <String> output = new ArrayList<>();
        for(Kaart krt : wedstrijdStapel){
            output.add(krt.getOmschrijving());
        }
        return output;
        
        
    }
    
    public void legWedstrijdkaart(String kaart, int keuze){
        
        for(Kaart krt : alleKaarten){
            if(krt.getOmschrijving().equals(kaart));
            wedstrijd.legWedstrijdkaart(krt, keuze);
        }
        
    }
    
    public String geefKaartType(String kaart){
        String kaartType="";
        for(Kaart krt: alleKaarten){
            if(krt.getOmschrijving().equals(kaart));
            kaartType = krt.getType();
        }
        return kaartType;
    }
 
    
    public String geefActieveSpeler(){
        return wedstrijd.geefActieveSpeler();
    }
    /*public List<String> registreerSpeler(String gebruikersnaam){
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if(speler != null && geregistreerdeSpelers.size() < 2){
            geregistreerdeSpelers.add(speler);
        }
        return new ArrayList<>(Arrays.asList(geregistreerdeSpelers.get(0).getGebruikersnaam(), geregistreerdeSpelers.get(1).getGebruikersnaam()));
    }*/
    // lijst van Strings 

    /**
     * Geeft de startstapel van de opgeven speler
     * @param naam van de speler
     * @return lijst van kaarten die de startstapel vormen voor de opgeven speler 
     */
    public List<String> toonStartStapel(String naam) {
        // Speler ophalen uit wedstrijd
        Speler spelerL = wedstrijd.geefSpeler(naam);
        List<String> output = new ArrayList<>();
        for (Kaart kaart : spelerL.getKaartLijst()) {
            output.add(kaart.getOmschrijving());
        }

        return output;
    }
    

    /**
     * Geeft het aantal kaarten in de wedstrijdstapel
     * @return aantal kaarten in de wedstrijdstapel
     */
    public int geefAantalKaartenWedstrijdStapel() {
        return speler.getWedstrijdStapel().size();
    }

    /**
     *Maakt een wedstrijdstapel aan
     * @param naam naam van de speler
     * @param stapel lijst van kaarten die de sstapel van de speler vormen
     */
    public void maakWedstrijdStapel(String naam, List<String> stapel) {

        wedstrijd.maakWedstrijdStapel(naam, stapel);
    }

    /**
     * Geeft het krediet van de winnaar
     * @return double krediet van de winnaar
     */
    public double geefKredietWinnaar() {
        return wedstrijd.getWinnaar().getKrediet();
    }

    /**
     * Geeft naam van de winnaar
     * @return String naam van de winnaar
     */
    public String geefNaamWinnaar() {
        return wedstrijd.getWinnaar().getGebruikersnaam();
    }

    /**
     * Verhoogt het krediet met 5
     */
    public void verhoogKrediet() {
        wedstrijd.verhoogKrediet();
    }

    /**
     * Verminderd het krediet van de opgegeven speler met de opgegeven prijs
     * @param prijs prijs van de kaart
     * @param speler speler
     */
    public void verminderKrediet(double prijs, String speler) {
        wedstrijd.verminderKrediet(prijs, speler);
    }

    /**
     * Geeft het krediet van de opgegeven speler
     * @param naam naam van de speler
     * @return double krediet van de meegegeven speler
     */
    public double geefKredietSpeler(String naam) {

        return wedstrijd.geefSpeler(naam).getKrediet();
    }

    /**
     * Toont alle kaarten waar de speler kan voor betalen
     * @return lijst van kaarten die betalend zijn
     */
    public List<String> toonBetalendeKaarten() { //UC7
        List<String> output1 = new ArrayList<>();
        geefAlleKaarten().forEach((kaart) -> {
            if (kaart.getPrijs() != 0) {
                output1.add(kaart.getOmschrijving() + " " + kaart.getPrijs());
            }
        });
        return output1;
    }
    

    /**
     * Voegt de betaalde kaart van de speler toe aan zijn startstapel
     * @param speler speler
     * @param omschrijving omschrijving van de kaart
     */
    public void voegBetaaldeKaartToeAanStartStapel(String speler, String omschrijving) { //UC7

        for (Kaart kaart : geefAlleKaarten()) {
            if (omschrijving.equals(kaart.getOmschrijving())) {
                wedstrijd.voegBetaaldeKaartenToeAanStartStapel(speler, kaart);   
            }
        }
    }
    
    /**
     * Maakt een setStapel
     */
    public void maakSetStapel() {
        
       wedstrijd.maakSetStapel();
    }
    
    /**
     * Bewaard de wedstrijd
     * @param naam naam van de speler
     */
    public void bewaarWedstrijd(String naam){
        //opslaan van de wedstrijd
        wedstrijdRepository.bewaarWedstrijd(naam, wedstrijd);
        
        for(String gNaam: wedstrijd.geefGeregistreerdeSpelers()){
            spelerRepository.updateSpeler(wedstrijd.geefSpeler(gNaam)); //update het krediet en de aangekochte kaarten van de speler
        }
    }
    
    /**
     * Toont een lijst van wedstrijden
     * @return lijst van wedstrijden
     */
    public List<String> toonLijstWedstrijden(){
        return wedstrijdRepository.toonLijstWedstrijden();
    }
    
    /**
     * Laad de wedstrijd
     * @param naam naam van de speler
     */
    public void laadWedstrijd(String naam){
        wedstrijd = wedstrijdRepository.laadWedstrijd(naam);
    }
    
    public void voegBovensteKaartVanSetStapelToeAanSpelbord(){
        wedstrijd.voegBovensteKaartVanSetStapelToeAanSpelbord();
    }
    
    public List<String> geefSpelbord(String speler){
//        List <Kaart> spelbord = speler.getSpelbord();
        List <Kaart> spelbord = wedstrijd.geefSpeler(speler).getSpelbord();

        List <String> output=new ArrayList<>();
        for(Kaart krt: spelbord){
            output.add(krt.getOmschrijving());
            
        }
        return output;
    }
    
    public void bepaalSpelerAanDeBeurtEersteSet(){
        wedstrijd.bepaalSpelerAanDeBeurtEersteSet();
    }
    
    public void bepaalSpelerAanVolgendeBeurt(){
        wedstrijd.bepaalSpelerAanVolgendeBeurt();
    }
    
    public List<String> geefSetStapel(){
        List<String> output = new ArrayList<>();
        for(Kaart kaart : wedstrijd.geefSetStapel()){
            output.add(kaart.getOmschrijving());
        }
        return output;
    }
    
    public int getAantalSets(){
        return wedstrijd.getAantalSets();
    }
    
    
    
    public int geefSetScore(String naam){
        return wedstrijd.geefSpeler(naam).getSetScore();
    }
    
    public void beeindigBeurt(){
        wedstrijd.beeindigBeurt();
    }
    
    
    public void bevriesSpelbord(){
        wedstrijd.bevriesSpelbord();
    }
    
    public boolean setEinde(){
        return wedstrijd.setEinde();
    }
    
    public void geefUitslag(){
        wedstrijd.geefUitslag();
    }
    
    
}
