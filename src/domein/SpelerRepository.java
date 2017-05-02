package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.SpelerMapper;

/**
 *
 * @author Jonas
 */
public class SpelerRepository {

    private final SpelerMapper mapper;
    private List<Speler> spelers;   //kan ook met een map gedaan worden, (naam, speler)

    /**
     *
     */
    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
        spelers = mapper.geefLijstSpelers();
    }

    /**
     *
     * @param speler
     */
    public void voegSpelerToe(Speler speler) {
        if (bestaatSpeler(speler.getGebruikersnaam())) {
            throw new IllegalArgumentException("naam_bestaat");
            
        }

        mapper.voegToe(speler);
        spelers.add(speler);
    }
    
    private boolean bestaatSpeler(String naam){
        for(Speler speler: spelers){
            if(speler.getGebruikersnaam().equals(naam)){ return true; }
        }
        return false;
    }

    /**
     *
     * @return
     */
    public List<String> geefLijstBeschikbareSpelers() {
        List<String> beschikbaar = new ArrayList<>();
        for(Speler speler: spelers){
            if(speler.isBeschikbaar()){ beschikbaar.add(speler.getGebruikersnaam()); }
        }
        return beschikbaar;
    }

    /**
     *
     * @param gebruikersnaam
     * @return
     */
    public Speler geefSpeler(String gebruikersnaam){
        for(Speler speler: spelers){
            if(speler.getGebruikersnaam().equals(gebruikersnaam)){ return speler; }
        }
        return null;
    }
    
    /**
     *
     * @param speler
     */
    public void updateSpeler(Speler speler){
        mapper.updateSpeler(speler); 
    }
    
}
