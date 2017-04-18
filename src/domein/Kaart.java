/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Jonas
 */
public class Kaart {

    private String omschrijving;
    private String type;
    private String waarde;
    private int prijs;

    public Kaart(String omschrijving, String type, String waarde, int prijs) {
        this.omschrijving = omschrijving;
        this.type = type;
        this.waarde = waarde;
        this.prijs = prijs;
    }

    public int getPrijs(){
        return prijs;
    }
    
    public String getOmschrijving() {
        return omschrijving;
    }

    public String getType() {
        return type;
    }

    public String getWaarde() {
        return waarde;
    }

}
