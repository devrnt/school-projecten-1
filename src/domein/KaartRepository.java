/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.KaartMapper;

/**
 *
 * @author Juan Carlos
 */
public class KaartRepository {

    private final KaartMapper mapper;

    /**
     * Default constructor
     */
    public KaartRepository() {
        mapper = new KaartMapper();
        //spelers = new ArrayList<>();
    }

    /*public void voegToe(Kaart kaart, Speler speler) {
        //kijken of er al default kaarten zijn toegevoegd
        //s
        //

        mapper.voegToe(kaart, speler);
    } */

    /**
     * Geeft een lijst van de startkaarten
     * @return lijst van startkaarten
     */

    public List<Kaart> getStartKaarten() {
        List<Kaart> startkaarten = new ArrayList<>();

        for (Kaart kaart : mapper.getKaarten()) {
            if (kaart.getPrijs() == 0) {
                startkaarten.add(kaart);
            }
        }

        return startkaarten;
    }

    /**
     * Geeft een lijst van alle kaarten
     * @return lijst van alle kaarten
     */
    public List<Kaart> getKaarten() {
        List<Kaart> kaarten = new ArrayList<>();

        for (Kaart kaart : mapper.getKaarten()) {
            kaarten.add(kaart);
        }

        return kaarten;
    }

    /**
     * Voegt de startkaarten toe aan de opgegeven speler
     * @param speler speler
     */
    public void voegStartkaartenToeAanSpeler(Speler speler) {
        mapper.voegStartkaartenToe(speler);
    }
}
