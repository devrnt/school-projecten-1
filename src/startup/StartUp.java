/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import ui.ConsoleApplicatie;

/**
 *
 * @author Jonas
 */
public class StartUp {
    public static void main(String[] args){
		DomeinController dc = new DomeinController();
		
                //testen Use Cases
                
                UC2.testResourceBundles(dc);
	
	}
}
