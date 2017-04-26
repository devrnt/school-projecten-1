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

    public Speler getWinnaar() {
        return winnaar;
    }

    public Wedstrijd() {

    }

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

    public String geefActieveSpeler() {
        return actief.getGebruikersnaam();
    }

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

    public void maakWedstrijdStapel(String naam, List<String> selectie) {
        geefSpeler(naam).maakWedstrijdStapel(selectie);
    }

    public void voegBetaaldeKaartenToeAanStartStapel(String naam, Kaart gekochteKaart) {

        List<Kaart> oudeStartStapel = geefSpeler(naam).getKaartLijst();
        List<Kaart> nieuweStartStapel = oudeStartStapel;
        nieuweStartStapel.add(gekochteKaart);

        geefSpeler(naam).setStartStapel(nieuweStartStapel);
    }

    public void verhoogKrediet() {
        winnaar.setKrediet(winnaar.getKrediet() + 5);
    }

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

    public void bepaalSpelerAanDeBeurtVolgendeSet() {
        if (actief == speler1) {
            actief = speler2;
        } else {
            actief = speler1;
        }
    }

    public void verminderKrediet(double prijs, String speler) {
        geefSpeler(speler).setKrediet(geefSpeler(speler).getKrediet() - prijs);
    }

    // mehtode schrijven die speler retourneert op basis van de naam
    public Speler geefSpeler(String naam) {
        if (naam.equals(speler1.getGebruikersnaam())) {
            return speler1;
        } else if (naam.equals(speler2.getGebruikersnaam())) {
            return speler2;
        }
        return null;
    }

    public void voegKaartToeAanStartStapel(Kaart kaart, String speler) {
        if (speler.equals(speler1.getGebruikersnaam())) {
            speler1.voegKaartToe(kaart);
        } else if (speler.equals(speler2.getGebruikersnaam())) {
            speler2.voegKaartToe(kaart);
        }
    }

    public void maakSetStapel() {
        setStapel = new ArrayList<>();
        for (int i = 1; i <= 4; i++) {
            for (int j = 1; j <= 10; j++) {
                setStapel.add(new Kaart("+" + j, "+", "" + j, 0));
            }
        }
        Collections.shuffle(setStapel);
    }

    public void voegBovensteKaartVanSetStapelToeAanSpelbord() {
        actief.voegBovensteKaartVanSetStapelToeAanSpelbord(setStapel.get(0));

        /* hieronder staat het als het een list van kaarten moet zijn */
        //actief.setStartStapel((List<Kaart>) setStapel.get(0)); //cast oke?
        setStapel.remove(0); // is het nodig om de kaart van de setstapel te verwijderen?
    }

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

    public void beeindigBeurt() {
        //nog schrijven
    }

    public void legWedstrijdkaart(Kaart kaart) {
        if (actief.getWedstrijdStapel().size() >= 0) {
            actief.voegKaartVanWedstrijdStapelToeAanSpelbord(kaart);
            actief.verwijderKaartVanWedstrijdStapel(kaart);
        }
    }

    public void bevriesSpelbord() {
        if (actief.isSpelbordBevroren() == false) {
            actief.setSpelbordBevroren(true);
        }
    }

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
