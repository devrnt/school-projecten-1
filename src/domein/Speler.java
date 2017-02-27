package domein;

import java.util.Calendar;

/**
 *
 * @author Jonas
 */
public class Speler {

    private String voornaam;
    private String achternaam;
    private double krediet;
    private int geboortejaar;
    private final int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);

    public Speler(String voornaam, String achternaam, int geboortejaar) {
        controleerVoornaam(voornaam);
        controleerAchternaam(achternaam);
        controleerLeeftijd(geboortejaar);

        this.voornaam = voornaam;
        this.achternaam = achternaam;
        this.geboortejaar = geboortejaar;
        krediet = 0;
    }

    public void controleerVoornaam(String voornaam) {
        if (voornaam.length() < 3) {
            throw new IllegalArgumentException("Je voornaam moet minstens uit 3 karakters bestaan.");
        }
        if (voornaam.contains(" ")) {
            throw new IllegalArgumentException("Uw voornaam mag geen spaties bevatten.");
        }

        //Controle maken voor leestekes in naam
        //if(voornaam.matches("\\p{Punct}")){
        //    throw new IllegalArgumentException("Uw voornaam mag geen leestekens bevatten.");
        //}
        //alleen nodig bij voornaam
        if (Character.isDigit(voornaam.charAt(0))) {
            throw new IllegalArgumentException("Uw voornaam mag niet beginnen met een cijfer");
        }

    }

    public void controleerAchternaam(String achternaam) {
        if (achternaam.length() < 3) {
            throw new IllegalArgumentException("Je voornaam moet minstens uit 3 karakters bestaan.");
        }

        if (achternaam.contains(" ")) {
            throw new IllegalArgumentException("Uw voornaam mag geen spaties bevatten.");
        }
    }

    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = huidigJaar - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("U moet ouder zijn dan 6 jaar.");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("U moet jonger zijn dan 99 jaar.");
        }
    }

    public String getVoornaam() {
        return voornaam;
    }

    public void setVoornaam(String voornaam) {
        controleerVoornaam(voornaam);
        this.voornaam = voornaam;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public void setAchternaam(String achternaam) {
        controleerAchternaam(achternaam);
        this.achternaam = achternaam;
    }

    public double getKrediet() {
        return krediet;
    }

    public void setKrediet(double krediet) {
        this.krediet = krediet;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public void setGeboortejaar(int geboortejaar) {
        controleerLeeftijd(geboortejaar);
        this.geboortejaar = geboortejaar;
    }

}
