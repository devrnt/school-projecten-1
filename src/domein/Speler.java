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
     *
     * @return
     */
    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    /**
     *
     * @param beschikbaar
     */
    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    /**
     *
     * @param gebruikersnaam
     * @param geboortejaar
     * @param krediet
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
     *
     * @return
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    /**
     *
     * @return
     */
    public double getKrediet() {
        return krediet;
    }

    /**
     *
     * @return
     */
    public boolean isSpelbordBevroren() {
        return spelbordBevroren;
    }

    /**
     *
     * @param krediet
     */
    public void setKrediet(double krediet) {
        this.krediet = krediet;
    }

    /**
     *
     * @param spelbordBevroren
     */
    public void setSpelbordBevroren(boolean spelbordBevroren) {
        this.spelbordBevroren = spelbordBevroren;
    }

    /**
     *
     * @return
     */
    public int getGeboortejaar() {
        return geboortejaar;
    }

    /**
     *
     * @return
     */
    public List<Kaart> getWedstrijdStapel() {
        return wedstrijdStapel;
    }

    /**
     *
     * @return
     */
    public int getScore() {
        return score;
    }

    /**
     *
     * @return
     */
    public List<Kaart> getSpelbord() {
        return spelbord;
    }
    
    //nog een methode voor de spelebord score

    /**
     *
     * @return
     */
    
    public int berekenScoreSpelbord(){
        //berekenscore
        return 0;
    }

    //later doen met reguliere expresies.

    /**
     *
     * @param gebruikersnaam
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
     *
     * @param geboortejaar
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
     *
     * @param lijst
     */
    public void setStartStapel(List<Kaart> lijst) {
        kaartLijst = lijst;
    }

    /**
     *
     * @param bovensteKaart
     */
    public void voegBovensteKaartVanSetStapelToeAanSpelbord(Kaart bovensteKaart) {
        if (spelbord.size() < 9) {
            spelbord.add(bovensteKaart);
        }
    }

    //kunnen samengevoegd worden

    /**
     *
     * @param kaart
     */
    public void voegKaartVanWedstrijdStapelToeAanSpelbord(Kaart kaart) {
        if (spelbord.size() < 9) {
            spelbord.add(kaart);
        }
    }

    /**
     *
     * @param kaart
     */
    public void verwijderKaartVanWedstrijdStapel(Kaart kaart) {
        for (Kaart k : wedstrijdStapel) {
            if (k.getOmschrijving().equals(kaart.getOmschrijving())) {
                wedstrijdStapel.remove(kaart);
            }

        }
    }

    /**
     *
     * @return
     */
    public List<Kaart> getKaartLijst() {
        return kaartLijst;
    }

    /**
     *
     * @param kaart
     */
    public void voegKaartToe(Kaart kaart) {
        kaartLijst.add(kaart);
    }

    /**
     *
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
