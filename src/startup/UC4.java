package startup;

import domein.DomeinController;
import java.util.Scanner;

public class UC4 {
     public static void testUC4(DomeinController dc) {
         
         int keuze = 0;
         Scanner input = new Scanner(System.in);
         
         do{
             System.out.println(dc.toonNGKaartenStartStapel());
             System.out.printf("U moet nog %d kaarten kiezen.%n", (6-dc.geefAantalKaartenWedstrijdStapel()));
             System.out.printf("> ");
             keuze = input.nextInt();
             dc.selecteerKaartVoorWedstrijdStapel(keuze);
             
             System.out.println("Uw kaart werd toegevoegd aan uw selectie voor de wedstrijdstapel!");
             
         }while(dc.geefAantalKaartenWedstrijdStapel()<6);
         
         dc.maakWedstrijdStapel();
    }
}
