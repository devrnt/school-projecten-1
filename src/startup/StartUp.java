/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ConsoleApplicatie;

/**
 *
 * @author Jonas
 */
public class StartUp {
    public static void main(String[] args){
		DomeinController dc = new DomeinController();
                
        try {
            Class.forName("com.mysql.jdbc.Driver");
            
            //testen Use Cases
            UC1.testUC1(dc);
            //UC2.testUC2(dc);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }
	
	}
}
