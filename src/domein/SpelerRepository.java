package domein;

import Exceptions.GebruikernaamInGebruikException;
import java.util.List;
import persistentie.SpelerMapper;

public class SpelerRepository {

    private final SpelerMapper mapper;

    public SpelerRepository() {
        mapper = new SpelerMapper();
        //spelers = new ArrayList<>();
    }

    public void voegSpelerToe(Speler speler) {
        if (bestaatSpeler(speler.getGebruikersnaam())) {
            throw new GebruikernaamInGebruikException();
            //throw new IllegalArgumentException("gebruikersnaam al in gebruik");//exception nog maken GebruikersnaamInGebruikException
        }

        mapper.voegToe(speler);

    }

    private boolean bestaatSpeler(String gebruikersnaam) {
        return mapper.geefSpeler(gebruikersnaam) != null;
    }
    
    public List<Speler> geefLijstBeschikbareSpelers(){
        //mapper.
                return null;
    }

}
