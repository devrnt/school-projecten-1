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
    private int waarde;

     public Kaart(String omschrijving, String type, int waarde){
         this.omschrijving = omschrijving;
         this.type = type;
         this.waarde = waarde;
              
        
    }
    
    
    public String getOmschrijving() {
        return omschrijving;
    }

    public String getType() {
        return type;
    }

    public int getWaarde() {
        return waarde;
    }
    
   
    
}
