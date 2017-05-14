/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
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
    private int aantalSets=1;
    private Speler winnaarSet;

    /**
     * Geeft de winnaar
     *
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
     *
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

    public List<Kaart> geefSetStapel() {
        return setStapel;
    }

    /**
     * Geeft de geregistreerde spelers
     *
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
     * Geeft het aantal sets van de wedstrijd
     *
     * @return int aantal sets van de wedstrijd
     */
    public int getAantalSets() {
        return aantalSets;
    }

    /**
     * Setter van aantalSets
     *
     * @param aantalSets aantal sets van de wedstrijd
     */
    public void setAantalSets(int aantalSets) {
        this.aantalSets = aantalSets;
    }

    /**
     * Geeft de actieve speler
     *
     * @return actieve speler
     */
    public String geefActieveSpeler() {
        return actief.getGebruikersnaam();
    }

    /**
     * Geeft de spelers zonder een wedstrijdstapel
     *
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
     *
     * @param naam naam van de speler
     * @param selectie selectie van de speler
     */
    public void maakWedstrijdStapel(String naam, List<String> selectie) {
        geefSpeler(naam).maakWedstrijdStapel(selectie);
    }

    /**
     * Voegt de betaalde kaarten toe aan de startstapel
     *
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
        if (aantalSets % 2 != 0) {
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
        } else if (aantalSets % 2 == 0) {

            if (actief == speler1) {
                actief = speler2;
            } else if (actief == speler2) {
                actief = speler1;
            }
        }
    }

    public void bepaalSpelerAanVolgendeBeurt() {
        if (speler2.isSpelbordBevroren()) {
            actief = speler1;
            return;
        }
        if (speler1.isSpelbordBevroren()) {
            actief = speler2;
            return;
        }
//        if (aantalSets == 0) {
//            bepaalSpelerAanDeBeurtEersteSet();
//            return;
//        } else 
        if (actief == speler1) {
            actief = speler2;
        } else {
            actief = speler1;
        }

//        if (aantalSets % 2 != 0) {
//            bepaalSpelerAanDeBeurtEersteSet();
//        }
//        if (aantalSets % 2 == 0) {
//                    if (actief == speler1) {
//                actief = speler2;
//            } else {
//                actief = speler1;
//            }
//        }
    }
    //methode volgende set bepaal actieve speler

    /**
     * Verminderd het krediet met de opgegeven hoeveelheid
     *
     * @param prijs aantal waarmee het krediet moet verminderd worden
     * @param speler speler waarvan het krediet moet verminderd worden
     */
    public void verminderKrediet(double prijs, String speler) {
        geefSpeler(speler).setKrediet(geefSpeler(speler).getKrediet() - prijs);
    }

    // mehtode schrijven die speler retourneert op basis van de naam
    /**
     * Geeft de speler
     *
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
     * Voegt een kaart toe aan de startstapel
     *
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
        actief.voegBovensteKaartVanSetStapelToeAanSpelbord(setStapel.get(0));//zet dit uit om te testen of het werkt in test.java
        //actief.verhoogSpelbordScore(Integer.parseInt(setStapel.get(0).getWaarde()));

        /* hieronder staat het als het een list van kaarten moet zijn */
        //actief.setStartStapel((List<Kaart>) setStapel.get(0)); //cast oke?
        setStapel.remove(0); // is het nodig om de kaart van de setstapel te verwijderen?
    }

    /**
     * Returned true als de set ten einde is
     *
     * @return true als de set ten einde is en false als de set niet ten einde
     * is
     */
    public boolean setEinde() {
        if (speler1.getSpelbordScore() > 20 || speler2.getSpelbordScore() > 20) {
            return true;
        }
        if (speler1.getSpelbord().size() >= 9 || speler2.getSpelbord().size() >= 9) {
            return true;
        }
        //als beide spelborden bevroren zijn
        return speler1.isSpelbordBevroren() && speler2.isSpelbordBevroren();

    }

    /**
     * beeindigd de beurt
     */
    public void beeindigBeurt() {
        bepaalSpelerAanVolgendeBeurt();
    }

    /**
     *
     * @param kaart
     * @param keuze
     */
    public void legWedstrijdkaart(Kaart kaart, int keuze) {

        if (actief.getWedstrijdStapel().size() > 0) {
            actief.voegKaartVanWedstrijdStapelToeAanSpelbord(kaart, keuze);
            actief.verwijderKaartVanWedstrijdStapel(kaart);
        }
    }

    /**
     * Bevriest het spelbord
     */
    public void bevriesSpelbord() {
        if (!actief.isSpelbordBevroren()) {
            actief.setSpelbordBevroren(true);
        }
        bepaalSpelerAanVolgendeBeurt();

    }

    public Speler getSpeler1() {
        return speler1;
    }

    public Speler getSpeler2() {
        return speler2;
    }

    public Speler getWinnaarSet() {
        return winnaarSet;
    }

    /**
     * Geeft de winnaar van de set
     */
    public void resetSet() {
//        List<Kaart> leegSpelbord = new ArrayList<>();
//        speler1.setSpelbordScore(0);
//        speler2.setSpelbordScore(0);
//        speler1.setSpelbord(leegSpelbord);
//        speler2.setSpelbord(leegSpelbord);
//        speler1.setSpelbordBevroren(false);
//        speler2.setSpelbordBevroren(false);
        geefSpeler(geefGeregistreerdeSpelers().get(0)).setSpelbordBevroren(false);
        geefSpeler(geefGeregistreerdeSpelers().get(0)).setSpelbordScore(0);
        geefSpeler(geefGeregistreerdeSpelers().get(0)).getSpelbord().clear();
        geefSpeler(geefGeregistreerdeSpelers().get(1)).setSpelbordBevroren(false);
        geefSpeler(geefGeregistreerdeSpelers().get(1)).setSpelbordScore(0);
        geefSpeler(geefGeregistreerdeSpelers().get(1)).getSpelbord().clear();
//        winnaarSet.setSpelbordScore(0);
//        winnaarSet.setSpelbord(null);
//        winnaarSet.setSpelbordBevroren(false);
    }

    public void geefUitslag() {
        int spelbordScoreSpeler1 = speler1.getSpelbordScore();
        int spelbordScoreSpeler2 = speler2.getSpelbordScore();

        int aantalKaartenSpelbordSpeler1 = speler1.getSpelbord().size();
        int aantalKaartenSpelbordSpeler2 = speler2.getSpelbord().size();

        if (aantalKaartenSpelbordSpeler1 == 9 && aantalKaartenSpelbordSpeler2 != 9) {

            if (spelbordScoreSpeler1 <= 20) {
                speler1.setSpelbordScore(spelbordScoreSpeler1);
                speler1.setSetScore(speler1.getSetScore() + 1);
                winnaarSet = speler1;

            }
        }

        if (aantalKaartenSpelbordSpeler2 == 9 && aantalKaartenSpelbordSpeler1 != 9) {

            if (spelbordScoreSpeler2 <= 20) {
                speler2.setSpelbordScore(spelbordScoreSpeler2);
                speler2.setSetScore(speler2.getSetScore() + 1);
                winnaarSet = speler2;

            }
        }

        if (aantalKaartenSpelbordSpeler1 <= 9 && aantalKaartenSpelbordSpeler2 <= 9) {
            if (spelbordScoreSpeler1 <= 20 && spelbordScoreSpeler2 <= 20) {
                if (spelbordScoreSpeler1 > spelbordScoreSpeler2) {
                    speler1.setSpelbordScore(spelbordScoreSpeler1);
                    speler1.setSetScore(speler1.getSetScore() + 1);
                    winnaarSet = speler1;

                } else if (spelbordScoreSpeler1 < spelbordScoreSpeler2) {
                    speler2.setSpelbordScore(speler2.getSpelbordScore());
                    speler2.setSetScore(speler2.getSetScore() + 1);
                    winnaarSet = speler2;

                } else {
                    winnaarSet = null;        //gelijkspel wordt afgehandeld in de dc
                }
            } else {
                winnaarSet = null;
            }
//            if (speler1.getSetScore() > 2) {
//                winnaarSet = speler1;
//            } else if (speler2.getSetScore() > 2) {
//                winnaarSet = speler2;
//            }
        } else {
            winnaarSet = null;
        }

        //hier wordt gekeken of er een winnaar is van de match.
        if (speler1.getSetScore() >= 3) {
            winnaar = speler1;
        }
        if (speler2.getSetScore() >= 3) {
            winnaar = speler2;
        }
    }
}
