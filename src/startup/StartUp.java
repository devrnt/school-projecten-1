/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.InputMismatchException;
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
        boolean flag = true;
        try {
            Class.forName("com.mysql.jdbc.Driver");

            //testen Use Cases
            UC2.testUC2(dc);

            while (flag) {
                do {
                    int i = 1;
                    System.out.printf("%d. %s%n", i, dc.getTaal().getVertaling("wedstrijd_start"));
                    i++;
                    System.out.printf("%d. %s%n", i, dc.getTaal().getVertaling("maak_speler"));
                    i++;
                    System.out.printf("%d. %s%n", i, dc.getTaal().getVertaling("laad_wedstrijd"));
                    i++;
                    System.out.printf("%d. %s%n", i, dc.getTaal().getVertaling("exit"));
                    i = 0;
                    System.out.println(dc.getTaal().getVertaling("selecteer_optie"));
                    System.out.print(" > ");
                    try {
                        keuze = input.nextInt();
                    } catch (InputMismatchException ex) {
                        System.err.println(dc.getTaal().getVertaling("integer_input"));
                        System.out.print(" > ");
                        input.nextLine();
                    }
                    System.out.println("--------------------");

                } while (keuze < 0 || keuze > 4);

                switch (keuze) {
                    case 1:
                        UC3.testUC3(dc);
                        break;
                    case 2:
                        UC1.testUC1(dc);
                        break;
                    case 3:
                        UC9.testUC9(dc);
                        break;
                    case 4:
                        flag = false;
                }

//                UC4.testUC4(dc);
                //UC2.testUC2(dc);
            }
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(StartUp.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
