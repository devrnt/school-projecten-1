package domein;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
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
    private int setScore = 0;
    private boolean spelbordBevroren;
    private int spelbordScore;

    /**
     * Return true als de speler beschikbaar is en false als de speler niet
     * beschikbaar is
     *
     * @return true als de speler beschikbaar is en false als de speler niet
     * beschikbaar is
     */
    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    /**
     * Zet de beschikbaarheid op true of false
     *
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
     *
     * @return String gebruikersnaam van de speler
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    /**
     * Geeft het krediet van de speler
     *
     * @return int krediet
     */
    public double getKrediet() {
        return krediet;
    }

    /**
     * Returned true als het spelbord is bevroren en false als het spelbord niet
     * is bevroren
     *
     * @return true als het spelbord is bevroren en false als het spelbord niet
     * is bevroren
     */
    public boolean isSpelbordBevroren() {
        return spelbordBevroren;
    }

    /**
     * Setter van krediet
     *
     * @param krediet krediet van de speler
     */
    public void setKrediet(double krediet) {
        this.krediet = krediet;
    }

    /**
     * setter van spelbordbevroren
     *
     * @param spelbordBevroren boolean of het spelbord is bevroren of niet
     */
    public void setSpelbordBevroren(boolean spelbordBevroren) {
        this.spelbordBevroren = spelbordBevroren;
    }

    /**
     * Geeft het geboortejaar van de speler
     *
     * @return int geboortejaar van de speler
     */
    public int getGeboortejaar() {
        return geboortejaar;
    }

    /**
     * Geeft de wedstrijdstapel van de speler
     *
     * @return lijst van de wedstrijdstapel
     */
    public List<Kaart> getWedstrijdStapel() {
        return wedstrijdStapel;
    }

    /**
     * Geeft de score van de speler
     *
     * @return int score van de speler
     */
    public int getSetScore() {
        return setScore;
    }

    /**
     * Geeft het spelbord
     *
     * @return lijst van kaarten dat het spelbord van de speler is
     */
    public List<Kaart> getSpelbord() {
        return spelbord;
    }

    //nog een methode voor de spelebord score
    /**
     * Berekend de score van het spelbord
     *
     * @return int score
     */
    public int berekenScoreSpelbord() {
        //berekenscore
        return 0;
    }

    //later doen met reguliere expresies.
    /**
     * Controleert de gebruikersnaam
     *
     * @param gebruikersnaam gebruikersnaam van de speler
     * @exception IllegalArgumentException Wanneer de invoer verkeerd is
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
     *
     * @param geboortejaar geboortejaar van de speler
     * @exception IllegalArgumentException Wanneer de invoer verkeerd is
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
     *
     * @param lijst lijst van kaarten
     */
    public void setStartStapel(List<Kaart> lijst) {
        kaartLijst = lijst;
    }

    /**
     * Voegt de bovenste kaart van set stapel toe aan spelbord
     *
     * @param bovensteKaart bovendstekaart
     */
    public void voegBovensteKaartVanSetStapelToeAanSpelbord(Kaart bovensteKaart) {
        if (getSpelbord().size() < 9) {
            spelbordScore += Integer.parseInt(bovensteKaart.getWaarde());
            spelbord.add(bovensteKaart);
        }
    }

    //kunnen samengevoegd worden
    /**
     * Voegt de meegegeven kaart van wedstrijdstapel toe aan spelbord
     *
     * @param kaart kaart
     * @param keuze
     */
    public void voegKaartVanWedstrijdStapelToeAanSpelbord(Kaart kaart, int keuze) {
        if (getSpelbord().size() < 9) {
            int scoreKaart = Integer.parseInt(kaart.getWaarde());
            switch (keuze) {
                case 3:
                    if (kaart.getType().equals("-")) {
                        spelbordScore = spelbordScore - scoreKaart;
                    } else if (kaart.getType().equals("+")) {
                        spelbordScore = spelbordScore + scoreKaart;
                    }
                    break;
                case 1://case 1 +
                    spelbordScore = spelbordScore + scoreKaart;
                    break;
                case 2: //case 2 -
                    spelbordScore = spelbordScore - scoreKaart;
                    break;

            }
            spelbord.add(kaart);

        }
//        else {
//            throw new IllegalArgumentException("spelbord_vol");
//
//        }
    }

    /**
     * Verwijderd de meegegeven kaart van de wedstrijdstapel
     *
     * @param kaart kaart
     */
    public void verwijderKaartVanWedstrijdStapel(Kaart kaart) {
//        if (wedstrijdStapel.size() > 0) {
//            Iterator<Kaart> iter = wedstrijdStapel.iterator();
//            while (iter.hasNext()) {
//                Kaart k = iter.next();
//                if (k.getOmschrijving().equals(kaart.getOmschrijving())) {
//                    wedstrijdStapel.remove(k);
//                }
//            }
//        }
        if(wedstrijdStapel.size()>0){
            for(Iterator<Kaart> it = wedstrijdStapel.iterator(); it.hasNext();){
                Kaart k = it.next();
                if(k.getOmschrijving().equals(kaart.getOmschrijving())){
                    it.remove();
                }
            }
        }
//        if (wedstrijdStapel.size() > 0) {
//            for (Kaart k : wedstrijdStapel) {
//                if (k.getOmschrijving().equals(kaart.getOmschrijving())) {
//                    wedstrijdStapel.remove(k);
//                }
//
//            }
//        }
//  else {
//            throw new IllegalArgumentException("geen_wedstrijdstapel");
//
//        }
    }

    /**
     * Geeft de KaartLijst
     *
     * @return lijst van kaarten
     */
    public List<Kaart> getKaartLijst() {
        return kaartLijst;
    }

    public void setSpelbordScore(int spelbordScore) {
        this.spelbordScore = spelbordScore;
    }

    /**
     * Voegt kaart toe aan kaartlijst
     *
     * @param kaart
     */
    public void voegKaartToe(Kaart kaart) {
        kaartLijst.add(kaart);
    }

    /**
     * Setter voor wedstrijdstapel (enkel voor wedstrijdmapper)
     * @param stapel
     */
    public void setWedstrijdStapel(List<Kaart> stapel){
        wedstrijdStapel = stapel;
    }
    
    /**
     * Maakt een WedstrijdStapel
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

    public int getSpelbordScore() {
        return spelbordScore;
    }

    public void setSetScore(int setScore) {
        this.setScore = setScore;
    }

    public void setSpelbord(List<Kaart> spelbord) {
        this.spelbord = spelbord;
    }
    
    

}
