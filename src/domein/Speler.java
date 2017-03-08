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

    public Speler(String gebruikersnaam, int geboortejaar, double krediet) {
        
        this.gebruikersnaam = gebruikersnaam;
        this.geboortejaar = geboortejaar;
        this.krediet = krediet;
    }

   
    public String getGebruikersnaam() {
        return gebruikersnaam;
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

    //later doen met reguliere expresies.
    public void controleerGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("naam_te_kort");
        }
        if (gebruikersnaam.contains(" ")) {
            throw new IllegalArgumentException("verkeerd_character");
        }

        //Controle maken voor leestekes in naam
        if(gebruikersnaam.matches("\\p{Punct}")){
            throw new IllegalArgumentException("verkeerd_character");
        }
        
        if (Character.isDigit(gebruikersnaam.charAt(0))) {
            throw new IllegalArgumentException("start_cijfer");
        }
    }
    
    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = Calendar.getInstance().get(Calendar.YEAR) - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("te_jong");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("te_oud");
        }
    }
}
