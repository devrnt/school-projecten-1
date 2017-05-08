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
     * Default constructor
     */
    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
        spelers = mapper.geefLijstSpelers();
    }

    /**
     * Voegt een nieuwe speler toe
     * @param speler speler
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
     * Geeft een lijst van alle beschikbare spelers
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
     * Geeft de speler aan de hand van de gebruikersnaam
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return Speler
     */
    public Speler geefSpeler(String gebruikersnaam){
        for(Speler speler: spelers){
            if(speler.getGebruikersnaam().equals(gebruikersnaam)){ return speler; }
        }
        return null;
    }
    
    /**
     * Update de Speler
     * @param speler speler
     */
    public void updateSpeler(Speler speler){
        mapper.updateSpeler(speler); 
    }
    
}
