package domein;

import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Jonas
 */
public class Speler {

    private String gebruikersnaam;
    private double krediet;
    private int geboortejaar;
    private List<Kaart> kaartLijst;
    private boolean beschikbaar;

    private List<Kaart> spelbord;    //max 9 kaarten
    private List<Kaart> wedstrijdStapel;
    private int score;
    private boolean spelbordBevroren;

    /**
     * Return true als de speler beschikbaar is en false als de speler niet beschikbaar is
     * @return true als de speler beschikbaar is en false als de speler niet beschikbaar is
     */
    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    /**
     * Zet de beschikbaarheid op true of false
     * @param beschikbaar boolean of de speler beschikbaar is of niet
     */
    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    /**
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param geboortejaar geboortejaar van de speler
     * @param krediet krediet van de speler
     */
    public Speler(String gebruikersnaam, int geboortejaar, double krediet) {

        wedstrijdStapel = new ArrayList<>();
        kaartLijst = new ArrayList<>();
        spelbord = new ArrayList<>();
        this.gebruikersnaam = gebruikersnaam;
        this.geboortejaar = geboortejaar;
        this.krediet = krediet;
        beschikbaar = true;
    }

    /**
     * Geeft de gebruikersnaam van de speler
     * @return String gebruikersnaam van de speler
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    /**
     * Geeft het krediet van de speler
     * @return int krediet
     */
    public double getKrediet() {
        return krediet;
    }

    /**
     * Returned true als het spelbord is bevroren en false als het spelbord niet is bevroren
     * @return true als het spelbord is bevroren en false als het spelbord niet is bevroren
     */
    public boolean isSpelbordBevroren() {
        return spelbordBevroren;
    }

    /**
     * Setter van krediet
     * @param krediet krediet van de speler
     */
    public void setKrediet(double krediet) {
        this.krediet = krediet;
    }

    /**
     * setter van spelbordbevroren
     * @param spelbordBevroren boolean of het spelbord is bevroren of niet
     */
    public void setSpelbordBevroren(boolean spelbordBevroren) {
        this.spelbordBevroren = spelbordBevroren;
    }

    /**
     * Geeft het geboortejaar van de speler
     * @return int geboortejaar van de speler
     */
    public int getGeboortejaar() {
        return geboortejaar;
    }

    /**
     * Geeft de wedstrijdstapel van de speler
     * @return lijst van de wedstrijdstapel
     */
    public List<Kaart> getWedstrijdStapel() {
        return wedstrijdStapel;
    }

    /**
     * Geeft de score van de speler
     * @return int score van de speler
     */
    public int getScore() {
        return score;
    }

    /**
     * Geeft het spelbord
     * @return lijst van kaarten dat het spelbord van de speler is
     */
    public List<Kaart> getSpelbord() {
        return spelbord;
    }
    
    //nog een methode voor de spelebord score

    /**
     * Berekend de score van het spelbord
     * @return int score
     */
    
    public int berekenScoreSpelbord(){
        //berekenscore
        return 0;
    }

    //later doen met reguliere expresies.

    /**
     * Controleert de gebruikersnaam
     * @param gebruikersnaam gebruikersnaam van de speler
     * @exception illegalArgumentException Wanneer de invoer verkeerd is
     */
    public void controleerGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("naam_te_kort");
        }

        if (gebruikersnaam.length() > 25) {
            throw new IllegalArgumentException("naam_te_lang");
        }

        if (gebruikersnaam.contains(" ")) {
            throw new IllegalArgumentException("verkeerd_character");
        }

        //Controle maken voor leestekes in naam
        Pattern p = Pattern.compile("[^A-Za-z0-9]");
        Matcher m = p.matcher(gebruikersnaam);
        if (m.find()) {
            throw new IllegalArgumentException("verkeerd_character");
        }

        if (Character.isDigit(gebruikersnaam.charAt(0))) {
            throw new IllegalArgumentException("start_cijfer");
        }
    }

    /**
     * Controleert de leeftijd
     * @param geboortejaar geboortejaar van de speler
     * @exception illegalArgumentException Wanneer de invoer verkeerd is
     */
    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = Calendar.getInstance().get(Calendar.YEAR) - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("te_jong");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("te_oud");
        }
    }

    /**
     * Setter van de startstapel
     * @param lijst lijst van kaarten
     */
    public void setStartStapel(List<Kaart> lijst) {
        kaartLijst = lijst;
    }

    /**
     * Voegt de bovenste kaart van set stapel toe aan spelbord
     * @param bovensteKaart bovendstekaart
     */
    public void voegBovensteKaartVanSetStapelToeAanSpelbord(Kaart bovensteKaart) {
        if (spelbord.size() < 9) {
            spelbord.add(bovensteKaart);
        }
    }

    //kunnen samengevoegd worden

    /**
     * Voegt de meegegeven kaart van wedstrijdstapel toe aan spelbord
     * @param kaart kaart
     */
    public void voegKaartVanWedstrijdStapelToeAanSpelbord(Kaart kaart) {
        if (spelbord.size() < 9) {
            spelbord.add(kaart);
        }
    }

    /**
     * Verwijderd de meegegeven kaart van de wedstrijdstapel
     * @param kaart kaart
     */
    public void verwijderKaartVanWedstrijdStapel(Kaart kaart) {
        for (Kaart k : wedstrijdStapel) {
            if (k.getOmschrijving().equals(kaart.getOmschrijving())) {
                wedstrijdStapel.remove(kaart);
            }

        }
    }

    /**
     * Geeft de KaartLijst
     * @return lijst van kaarten
     */
    public List<Kaart> getKaartLijst() {
        return kaartLijst;
    }

    /**
     * Voegt kaart toe aan kaartlijst
     * @param kaart
     */
    public void voegKaartToe(Kaart kaart) {
        kaartLijst.add(kaart);
    }

    /**
     * Maakt een WedstrijdStapel
     * @param selectie
     */
    public void maakWedstrijdStapel(List<String> selectie) {
        kaartLijst.forEach((kaart) -> {
            for (int i = 0; i < selectie.size(); i++) {
                if (kaart.getOmschrijving().equals(selectie.get(i))) {
                    wedstrijdStapel.add(kaart);
                }
            }
        });
        Random random = new Random();

        while (wedstrijdStapel.size() > 4) {

            wedstrijdStapel.remove(random.nextInt(wedstrijdStapel.size()));

        }
    }
}
