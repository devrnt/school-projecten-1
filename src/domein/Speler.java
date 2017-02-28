package domein;

import java.util.Calendar;

/**
 *
 * @author Jonas
 */
public class Speler {

    private String gebruikersnaam;
    private double krediet;
    private int geboortejaar;

    public Speler(String gebruikersnaam, int geboortejaar) {
        controleerGebruikersnaam(gebruikersnaam);
        controleerLeeftijd(geboortejaar);

        this.gebruikersnaam = gebruikersnaam;
        this.geboortejaar = geboortejaar;
        krediet = 0;
    }

    //later doen met reguliere expresies.
    public void controleerGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("Je voornaam moet minstens uit 3 karakters bestaan.");
        }
        if (gebruikersnaam.contains(" ")) {
            throw new IllegalArgumentException("Uw voornaam mag geen spaties bevatten.");
        }

        //Controle maken voor leestekes in naam
        //if(voornaam.matches("\\p{Punct}")){
        //    throw new IllegalArgumentException("Uw voornaam mag geen leestekens bevatten.");
        //}
        //alleen nodig bij voornaam
        if (Character.isDigit(gebruikersnaam.charAt(0))) {
            throw new IllegalArgumentException("Uw voornaam mag niet beginnen met een cijfer");
        }
        
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("Je voornaam moet minstens uit 3 karakters bestaan.");
        }

        if (gebruikersnaam.contains(" ")) {
            throw new IllegalArgumentException("Uw voornaam mag geen spaties bevatten.");
        }

    }


    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = Calendar.getInstance().get(Calendar.YEAR) - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("U moet ouder zijn dan 6 jaar.");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("U moet jonger zijn dan 99 jaar.");
        }
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        controleerGebruikersnaam(gebruikersnaam);
        this.gebruikersnaam = gebruikersnaam;
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
