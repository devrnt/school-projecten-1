/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.List;

/**
 *
 * @author Jonas
 */
public class Wedstrijd {
    
    private Speler speler1;
    private Speler speler2;
    
    public Wedstrijd(List<Speler> spelers){
        speler1 = spelers.get(0);
        speler2 = spelers.get(1);
    }
    
}
