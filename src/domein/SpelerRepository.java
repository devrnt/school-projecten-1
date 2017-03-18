package domein;

import Exceptions.GebruikernaamInGebruikException;
import java.util.ArrayList;
import java.util.List;
import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;
    private List<Speler> spelers;   //kan ook met een map gedaan worden, (naam, speler)

    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
        spelers = mapper.geefLijstSpelers();
    }

    public void voegSpelerToe(Speler speler) {
        if (mapper.bestaatSpeler(speler.getGebruikersnaam())) {
            throw new GebruikernaamInGebruikException();
            //throw new IllegalArgumentException("gebruikersnaam al in gebruik");//exception nog maken GebruikersnaamInGebruikException
        }

        mapper.voegToe(speler);
        spelers.add(speler);
    }

    public List<String> geefLijstBeschikbareSpelers() {
        List<String> beschikbaar = new ArrayList<>();
        for(Speler speler: spelers){
            if(speler.isBeschikbaar()){ beschikbaar.add(speler.getGebruikersnaam()); }
        }
        return beschikbaar;
    }

}
