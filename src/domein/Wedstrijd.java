/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class Wedstrijd {

    private Speler speler1; //werken met gewoon de userName ipv de volledige speler?
    private Speler speler2;
    private Speler actief;
    private Speler winnaar;
    private List<Kaart> setStapel;
    private int aantalSets;

    /**
     * Geeft de winnaar
     * @return Speler winnaar
     */
    public Speler getWinnaar() {
        return winnaar;
    }

    /**
     * Default constructor
     */
    public Wedstrijd() {

    }

    /**
     * Registreert de speler
     * @param speler speler
     */
    public void registreerSpeler(Speler speler) {
        if (speler != null) {
            if (speler1 == null) {
                speler1 = speler;
                speler.setBeschikbaar(false);
            } else if (speler2 == null) {
                speler.setBeschikbaar(false);
                speler2 = speler;
            }
        }
    }

    /**
     * Geeft de geregistreerde spelers
     * @return lijst van alle geregistreerde spelers
     */
    public List<String> geefGeregistreerdeSpelers() {
        List<String> lijst = new ArrayList<>();
        if (speler1 != null) {
            if (speler2 != null) {
                lijst.add(speler2.getGebruikersnaam());
            }
            lijst.add(speler1.getGebruikersnaam());
        }
        return lijst;
    }

    /**
     * Geeft de actieve speler
     * @return actieve speler
     */
    public String geefActieveSpeler() {
        return actief.getGebruikersnaam();
    }

    /**
     * Geeft de spelers zonder een wedstrijdstapel
     * @return lijst van spelers zonder wedstrijdstapel
     */
    public List<String> geefSpelersZonderWedstrijdStapel() {
        List<String> lijst = new ArrayList<>();
        if (speler1.getWedstrijdStapel().isEmpty()) {
            lijst.add(speler1.getGebruikersnaam());
        }
        if (speler2.getWedstrijdStapel().isEmpty()) {
            lijst.add(speler2.getGebruikersnaam());
        }
        return lijst;
    }

    /**
     * Maakt een wedstrijdstapel
     * @param naam naam van de speler
     * @param selectie selectie van de speler
     */
    public void maakWedstrijdStapel(String naam, List<String> selectie) {
        geefSpeler(naam).maakWedstrijdStapel(selectie);
    }

    /**
     * Voegt de betaalde kaarten toe aan de startstapel
     * @param naam van de speler
     * @param gekochteKaart kaart die de speler heeft gekocht
     */
    public void voegBetaaldeKaartenToeAanStartStapel(String naam, Kaart gekochteKaart) {

        geefSpeler(naam).getKaartLijst().add(gekochteKaart);

    }

    /**
     * Verhoogt het krediet van de winnaar met 5
     */
    public void verhoogKrediet() {
        winnaar.setKrediet(winnaar.getKrediet() + 5);
    }

    /**
     * Bepaald Speler Aan de Beurt van de eerste set
     */
    public void bepaalSpelerAanDeBeurtEersteSet() {
        int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);

        int leeftijdSpeler1 = huidigJaar - speler1.getGeboortejaar();
        int leeftijdSpeler2 = huidigJaar - speler2.getGeboortejaar();

        if (leeftijdSpeler1 > leeftijdSpeler2) {
            actief = speler1;
        } else if (leeftijdSpeler2 > leeftijdSpeler1) {
            actief = speler2;
        } else if (leeftijdSpeler1 == leeftijdSpeler2) {
            int compare = speler1.getGebruikersnaam().compareTo(speler2.getGebruikersnaam());

            if (compare < 0) {
                actief = speler1;
            } else if (compare > 0) {
                actief = speler2;
            }
        }
    }

    /**
     * Bepaald speler aan de beurt van de volgende set
     */
    public void bepaalSpelerAanDeBeurtVolgendeSet() {
        if (actief == speler1) {
            actief = speler2;
        } else {
            actief = speler1;
        }
    }

    /**
     * Verminderd het krediet met de opgegeven hoeveelheid
     * @param prijs aantal waarmee het krediet moet verminderd worden
     * @param speler speler waarvan het krediet moet verminderd worden
     */
    public void verminderKrediet(double prijs, String speler) {
        geefSpeler(speler).setKrediet(geefSpeler(speler).getKrediet() - prijs);
    }

    // mehtode schrijven die speler retourneert op basis van de naam

    /**
     * Geeft de speler
     * @param naam naam van de speler
     * @return speler
     */
    public Speler geefSpeler(String naam) {
        if (naam.equals(speler1.getGebruikersnaam())) {
            return speler1;
        } else if (naam.equals(speler2.getGebruikersnaam())) {
            return speler2;
        }
        return null;
    }

    /**
     * Voegt een kaart toe aan de  startstapel
     * @param kaart kaart die aan de startstapel moet worden toegevoegd
     * @param speler speler
     */
    public void voegKaartToeAanStartStapel(Kaart kaart, String speler) {
        if (speler.equals(speler1.getGebruikersnaam())) {
            speler1.voegKaartToe(kaart);
        } else if (speler.equals(speler2.getGebruikersnaam())) {
            speler2.voegKaartToe(kaart);
        }
    }

    /**
     * Maakt een setstapel
     */
    public void maakSetStapel() {
        setStapel = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 10; j++) {
                setStapel.add(new Kaart("+" + j, "+", "" + j, 0));
            }
        }
        Collections.shuffle(setStapel);
    }

    /**
     * Voegt de bovenste kaart van set stapel toe aan het spelbord
     */
    public void voegBovensteKaartVanSetStapelToeAanSpelbord() {
        actief.voegBovensteKaartVanSetStapelToeAanSpelbord(setStapel.get(0));

        /* hieronder staat het als het een list van kaarten moet zijn */
        //actief.setStartStapel((List<Kaart>) setStapel.get(0)); //cast oke?
        setStapel.remove(0); // is het nodig om de kaart van de setstapel te verwijderen?
    }

    /**
     * Returned true als de set ten einde is
     * @return true als de set ten einde is en false als de set niet ten einde is
     */
    public boolean setEinde() {
        if (speler1.getScore() > 20 || speler2.getScore() > 20) {
            return true;
        }
        if (speler1.getSpelbord().size() >= 9 || speler2.getSpelbord().size() >= 9) {
            return true;
        }
        if (speler1.isSpelbordBevroren() == true && speler2.isSpelbordBevroren() == true) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * beeindigd de beurt
     */
    public void beeindigBeurt() {
        //nog schrijven
    }

    /**
     * 
     * @param kaart
     */
    public void legWedstrijdkaart(Kaart kaart) {
        if (actief.getWedstrijdStapel().size() >= 0) {
            actief.voegKaartVanWedstrijdStapelToeAanSpelbord(kaart);
            actief.verwijderKaartVanWedstrijdStapel(kaart);
        }
    }

    /**
     * Bevriest het spelbord
     */
    public void bevriesSpelbord() {
        if (actief.isSpelbordBevroren() == false) {
            actief.setSpelbordBevroren(true);
        }
    }

    /**
     * Geeft de winnaar van de set
     * @return winnaar van de set
     */
    public Speler geefUitslag() {
        int scoreSpeler1 = speler1.getScore();
        int scoreSpeler2 = speler2.getScore();

        int aantalKaartenSpelbordSpeler1 = speler1.getWedstrijdStapel().size();
        int aantalKaartenSpelbordSpeler2 = speler2.getWedstrijdStapel().size();

        boolean bSpeler1;
        boolean bSpeler2;

        if (aantalKaartenSpelbordSpeler1 == 9 || aantalKaartenSpelbordSpeler2 == 9) {
            if (aantalKaartenSpelbordSpeler1 == 9) {
                if (scoreSpeler1 <= 20) {
                    return speler1;
                }
            }
            if (aantalKaartenSpelbordSpeler2 == 9) {
                if (scoreSpeler2 <= 20) {
                    return speler2;
                }

            }
        }

        return actief;//tijdelijk
    }
}
