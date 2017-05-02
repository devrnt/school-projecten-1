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
    
    public WedstrijdRepository(){
        mapper = new WedstrijdMapper();
    }
    
    public void bewaarWedstrijd(String naam, Wedstrijd wedstrijd){
        mapper.bewaarWedstrijd(naam, wedstrijd);
    }
    
    public List<String> toonLijstWedstrijden(){
        return mapper.toonWedstrijdLijst();
    }
    
    public Wedstrijd laadWedstrijd(String naam){
        return null;
    }
    
}
