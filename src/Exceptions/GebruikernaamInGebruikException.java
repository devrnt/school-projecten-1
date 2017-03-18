/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Edward
 */
public class GebruikernaamInGebruikException extends IllegalArgumentException{

    public GebruikernaamInGebruikException() {
        super("Geef een getal in.");
    }

    public GebruikernaamInGebruikException(String s) {
        super(s);
    }

    public GebruikernaamInGebruikException(String message, Throwable cause) {
        super(message, cause);
    }

    public GebruikernaamInGebruikException(Throwable cause) {
        super(cause);
    }
    
    
}
