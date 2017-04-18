package domein;

import static java.lang.Math.floor;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
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
    private List<Kaart> wedstrijdStapel;

    public boolean isBeschikbaar() {
        return beschikbaar;
    }

    public void setBeschikbaar(boolean beschikbaar) {
        this.beschikbaar = beschikbaar;
    }

    public Speler(String gebruikersnaam, int geboortejaar, double krediet) {

        wedstrijdStapel = new ArrayList<>();
        kaartLijst = new ArrayList<>();
        this.gebruikersnaam = gebruikersnaam;
        this.geboortejaar = geboortejaar;
        this.krediet = krediet;
        beschikbaar = true;
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

    public List<Kaart> getWedstrijdStapel() {
        return wedstrijdStapel;
    }

    //later doen met reguliere expresies.
    public void controleerGebruikersnaam(String gebruikersnaam) {
        if (gebruikersnaam.length() < 3) {
            throw new IllegalArgumentException("naam_te_kort");
        }
        
        if(gebruikersnaam.length()>25){
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

    public void controleerLeeftijd(int geboortejaar) {
        int leeftijd = Calendar.getInstance().get(Calendar.YEAR) - geboortejaar;
        if (leeftijd < 6) {
            throw new IllegalArgumentException("te_jong");
        } else if (leeftijd > 99) {
            throw new IllegalArgumentException("te_oud");
        }
    }

    public void setStartStapel(List<Kaart> lijst) {
        kaartLijst = lijst;
    }

    public List<Kaart> getKaartLijst() {
        return kaartLijst;
    }
    
    public void voegKaartToe(Kaart kaart){
        kaartLijst.add(kaart);
    }

    public void maakWedstrijdStapel(List<String> selectie) {
        // met for - lus de stapel overlopen + vergelijken met de startstapel en zo de wedstrijdstapel opbouwen
        int min = 0;
        int max = wedstrijdStapel.size();

        kaartLijst.forEach((kaart) -> {
            for (int i = 0; i < selectie.size(); i++) {
                if (kaart.getOmschrijving() == selectie.get(i)) {
                    wedstrijdStapel.add(kaart);
                }
            }
        });

        while (wedstrijdStapel.size() > 4) {

            int randomNum = (int) floor(Math.random()*7);

            wedstrijdStapel.remove(randomNum);

        }
    }

}
