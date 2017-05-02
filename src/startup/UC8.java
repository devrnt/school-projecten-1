/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package startup;

import domein.DomeinController;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Edward
 */
public class UC8 {

    public static void testUC8(DomeinController dc) {
        Scanner input = new Scanner(System.in);
        int keuze = 0;

        System.out.println(dc.getTaal().getVertaling("unieke_naamWedstrijd"));
        System.out.printf(" > ");
        String naamWedstrijd = input.next();

        dc.bewaarWedstrijd(naamWedstrijd);
        System.out.println(dc.getTaal().getVertaling("verder_spelen"));
        System.out.println(dc.getTaal().getVertaling("ja"));
        System.out.println(dc.getTaal().getVertaling("nee"));

        while (keuze < 1 || keuze > 2) {
            try {
                System.out.printf(" > ");
                keuze = input.nextInt();
            } catch (InputMismatchException e) {
                System.err.println("Not a valid input. Error: " + e.getMessage());
            }
        }

        switch (keuze) {
            case 1: return;
            case 2: System.out.println(dc.getTaal().getVertaling("afsluiten")); //wordt heel het programma afgesloten of wat gebeurt er hier?
                System.exit(0);
        }

    }
}
