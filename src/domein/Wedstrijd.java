/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class Wedstrijd {
    
    private Speler speler1; //werken met gewoon de userName ipv de volledige speler?
    private Speler speler2;
    private Speler actief;
    
    public Wedstrijd(){
        
    }
    
    public List<String> registreerSpeler(Speler speler){
        if(speler != null){
            if(speler1 == null){
                speler1 = speler;
                speler.setBeschikbaar(false);
                return new ArrayList<>(Arrays.asList(speler1.getGebruikersnaam()));
            }else if(speler2 == null){
                speler.setBeschikbaar(false);
                speler2 = speler;
            }
        }
        return new ArrayList<>(Arrays.asList(speler1.getGebruikersnaam(), speler2.getGebruikersnaam()));
    }
    
    public String geefActieveSpeler(){
        return actief.getGebruikersnaam();
    }
    
    public List<String> geefSpelersZonderWedstrijdStapel(){
        //toDo
        return new ArrayList<>();
    }
}
