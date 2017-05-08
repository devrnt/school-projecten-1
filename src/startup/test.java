/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import domein.Speler;
import domein.SpelerRepository;
import domein.Wedstrijd;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jonas
 */
public class test {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
         

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //testen Use Cases
//            UC6.testUC6(dc);

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
