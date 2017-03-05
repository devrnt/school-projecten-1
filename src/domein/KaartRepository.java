/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.KaartMapper;

/**
 *
 * @author Juan Carlos
 */
public class KaartRepository {
    private final KaartMapper mapper;

    public KaartRepository() {
        mapper = new KaartMapper();
        //spelers = new ArrayList<>();
    }
    
    public void voegToe(Kaart kaart, Speler speler){
        //kijken of er al default kaarten zijn toegevoegd
        //s
        //
        
        mapper.voegToe(kaart, speler);
    }
    
}
