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
        if (mapper.bestaatSpeler(speler.getGebruikersnaam())) {
            throw new GebruikernaamInGebruikException();
            //throw new IllegalArgumentException("gebruikersnaam al in gebruik");//exception nog maken GebruikersnaamInGebruikException
        }

        mapper.voegToe(speler);

    }

    public List<String> geefLijstBeschikbareSpelers() {
        return mapper.geefLijstBeschikbareSpelers();
    }

}
