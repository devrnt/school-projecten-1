/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class Set {

    private Wedstrijd wedstrijd;
    private SpelerRepository spelerRepo;
    int huidigJaar = Calendar.getInstance().get(Calendar.YEAR);
    private int setRonde;

    public Set() {

    }

    public void maakSetStapel() {

    }

    public String bepaalActieveSpeler() {
        String actieveSpeler = null;
        List<String> spelers = wedstrijd.geefGeregistreerdeSpelers();
        String[] spelersStringList = spelers.toArray(new String[0]);

        Speler speler1 = spelerRepo.geefSpeler(spelersStringList[0]);
        Speler speler2 = spelerRepo.geefSpeler(spelersStringList[1]);

        if (setRonde == 1) {

            int leeftijdSpeler1 = huidigJaar - speler1.getGeboortejaar();
            int leeftijdSpeler2 = huidigJaar - speler2.getGeboortejaar();

            if (leeftijdSpeler1 > leeftijdSpeler2) {
                return speler1.getGebruikersnaam();
            } else if (leeftijdSpeler1 == leeftijdSpeler2) {
                int compare = speler1.getGebruikersnaam().compareTo(speler2.getGebruikersnaam());

                if (compare < 0) {
                    return speler1.getGebruikersnaam();
                } else if (compare > 0) {
                    return speler2.getGebruikersnaam();
                } else {
                    //als het gelijk is nog iets vinden 
                }
            } else {
                return speler2.getGebruikersnaam();
            }

        }

        if (setRonde % 2 == 0) {
            if (bepaalActieveSpeler() == speler1.getGebruikersnaam()) {
                return speler2.getGebruikersnaam();
            } else {
                return speler1.getGebruikersnaam();
            }
        }
        
        if(setRonde % 2 != 0){
            if(bepaalActieveSpeler()== speler1.getGebruikersnaam()){
                return speler2.getGebruikersnaam();
            } else {
                speler1.getGebruikersnaam();
            }
        }//niet oke, gewoon een test

        return actieveSpeler;

        //we kunnen ook een speler teruggeven
    }

}
