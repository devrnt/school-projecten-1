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

    /**
     *
     * @param omschrijving omschrijving van de kaart
     * @param type type van de kaart
     * @param waarde waarde van de kaart
     * @param prijs prijs van de kaart
     */
    public Kaart(String omschrijving, String type, String waarde, int prijs) {
        this.omschrijving = omschrijving;
        this.type = type;
        this.waarde = waarde;
        this.prijs = prijs;
    }

    /**
     * Geeft de prijs van de kaart
     * @return int prijs van de kaart
     */
    public int getPrijs(){
        return prijs;
    }
    
    /**
     * Geeft de omschrijving van de kaart
     * @return String omschrijving van de kaart
     */
    public String getOmschrijving() {
        return omschrijving;
    }

    /**
     * Geeft de type van de kaart
     * @return String type van de kaart
     */
    public String getType() {
        return type;
    }

    /**
     * Geeft de waarde van de kaart
     * @return String waarde van de kaart
     */
    public String getWaarde() {
        return waarde;
    }

}
