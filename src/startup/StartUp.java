/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import ui.ConsoleApplicatie;

/**
 *
 * @author Jonas
 */
public class StartUp {

    public static void main(String[] args) {
        DomeinController dc = new DomeinController();
        Scanner input = new Scanner(System.in);
        int keuze = -1;

        try {
            Class.forName("com.mysql.jdbc.Driver");

            //testen Use Cases
            UC2.testUC2(dc);
            do {
                System.out.println("Selecteer een optie");
                System.out.println(">");
                keuze = input.nextInt();
                
            } while (keuze < 0 || keuze > 4);
            
            switch(keuze){
                case 1: UC1.testUC1(dc);
            }
            
            UC3.testUC3(dc);
            //UC4.testUC4(dc);
            //UC2.testUC2(dc);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
