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
    private Speler winnaar;

    public Speler getWinnaar() {
        return winnaar;
    }
    
    public Wedstrijd(){
        
    }
    
    public void registreerSpeler(Speler speler){
        if(speler != null){
            if(speler1 == null){
                speler1 = speler;
                speler.setBeschikbaar(false);
            }else if(speler2 == null){
                speler.setBeschikbaar(false);
                speler2 = speler;
            }
        }
    }
    
    public List<String> geefGeregistreerdeSpelers(){
        List<String> lijst = new ArrayList<>();
        if(speler1 != null){
            if(speler2 != null){ lijst.add(speler2.getGebruikersnaam()); }
            lijst.add(speler1.getGebruikersnaam());
        }
        return lijst;
    }
    
    public String geefActieveSpeler(){
        return actief.getGebruikersnaam();
    }
    
    public List<String> geefSpelersZonderWedstrijdStapel(){
        List<String> lijst = new ArrayList<>();
        if(speler1.getWedstrijdStapel().isEmpty()){ lijst.add(speler1.getGebruikersnaam()); }
        if(speler2.getWedstrijdStapel().isEmpty()){ lijst.add(speler2.getGebruikersnaam()); }
        return lijst;
    }
    public void maakWedstrijdStapel(String naam, List<String> selectie) {
        if (naam.equals(speler1.getGebruikersnaam())){
            speler1.maakWedstrijdStapel(selectie);
        }
        else if (naam.equals(speler2.getGebruikersnaam())){
            speler2.maakWedstrijdStapel(selectie);
        }
    }
    
    public void verhoogKrediet(){
        winnaar.setKrediet(winnaar.getKrediet() + 5);
    }
}
