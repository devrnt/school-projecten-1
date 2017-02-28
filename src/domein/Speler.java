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

}
