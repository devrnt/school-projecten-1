/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;
import persistentie.WedstrijdMapper;

/**
 *
 * @author Edward
 */
public class WedstrijdRepository {
    
    private WedstrijdMapper mapper;
    
    /**
     * Default constructor
     */
    public WedstrijdRepository(){
        mapper = new WedstrijdMapper();
    }
    
    /**
     * Bewaart De wedstrijd
     * @param naam naam van de speler
     * @param wedstrijd naam van de wedstrijd
     */
    public void bewaarWedstrijd(String naam, Wedstrijd wedstrijd){
        mapper.bewaarWedstrijd(naam, wedstrijd);
    }
    
    /**
     * Toont de lijst van wedstrijden
     * @return lijst van wedstrijden
     */
    public List<String> toonLijstWedstrijden(){
        return mapper.toonWedstrijdLijst();
    }
    
    /**
     * Laad de wedstrijd
     * @param naam van de speler die de wedstrijd wil laden
     * @return wedstrijd
     */
    public Wedstrijd laadWedstrijd(String naam){
        return mapper.laadWedstrijd(naam);
    }
    
}
