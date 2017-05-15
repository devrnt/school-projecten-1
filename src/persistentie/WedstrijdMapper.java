/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kaart;
import domein.Speler;
import domein.Wedstrijd;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Edward
 */
public class WedstrijdMapper {

    /**
     * Bewaart de opgegeven wedstrijd in de databank
     * @param naam
     * @param wedstrijd
     */
    public void bewaarWedstrijd(String naam, Wedstrijd wedstrijd) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijd (naam, aantalSets)"
                    + "VALUES (?, ?)");

            query.setString(1, naam);
            query.setInt(2, wedstrijd.getAantalSets());
            query.executeUpdate();

            PreparedStatement querySpelerID = conn.prepareStatement("SELECT spelerID FROM ID222177_g14.Speler WHERE gebruikersnaam = ?");
            querySpelerID.setString(1, wedstrijd.getSpeler1().getGebruikersnaam());
            int id1;
            try (ResultSet rs = querySpelerID.executeQuery()) {
                rs.next();
                id1 = rs.getInt("spelerID");
            }
            querySpelerID.setString(1, wedstrijd.getSpeler2().getGebruikersnaam());
            int id2;
            try (ResultSet rs = querySpelerID.executeQuery()) {
                rs.next();
                id2 = rs.getInt("spelerID");
            }

            PreparedStatement querySpeler1 = conn.prepareStatement("INSERT INTO ID222177_g14.Score (spelerID, naam, score)"
                    + "VALUES (?, ?, ?)");
            querySpeler1.setInt(1, id1);
            querySpeler1.setString(2, naam);
            querySpeler1.setInt(3, wedstrijd.getSpeler1().getSetScore());
            querySpeler1.executeUpdate();

            PreparedStatement querySpeler2 = conn.prepareStatement("INSERT INTO ID222177_g14.Score (spelerID, naam, score)"
                    + "VALUES (?, ?, ?)");
            querySpeler2.setInt(1, id2);
            querySpeler2.setString(2, naam);
            querySpeler2.setInt(3, wedstrijd.getSpeler2().getSetScore());
            querySpeler2.executeUpdate();

            PreparedStatement queryStapelSpeler1 = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijdstapel (spelerID, naam, omschrijving)"
                    + "VALUES (?, ?, ?)");
            queryStapelSpeler1.setInt(1, id1);
            queryStapelSpeler1.setString(2, naam);
            for (Kaart kaart : wedstrijd.getSpeler1().getWedstrijdStapel()) {
                queryStapelSpeler1.setString(3, kaart.getOmschrijving());
                queryStapelSpeler1.executeUpdate();                
            }
            PreparedStatement queryStapelSpeler2 = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijdstapel (spelerID, naam, omschrijving)"
                    + "VALUES (?, ?, ?)");
            queryStapelSpeler2.setInt(1, id2);
            queryStapelSpeler2.setString(2, naam);
            for (Kaart kaart : wedstrijd.getSpeler2().getWedstrijdStapel()) {
                queryStapelSpeler2.setString(3, kaart.getOmschrijving());
                queryStapelSpeler2.executeUpdate();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    /**
     * Toont een lijst van de opgeslagen wedstrijden
     * @return lijst van opgeslagen wedstrijden
     */
    public List<String> toonWedstrijdLijst() {
        List<String> wedstrijdLijst = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT naam FROM ID222177_g14.Wedstrijd");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    wedstrijdLijst.add(rs.getString("naam"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return wedstrijdLijst;
    }

    /**
     * Laad de wedstrijd uit de databank
     * @param naam naam van de wedstrijd
     * @return wedstrijd die opgeslagen is in de databank aan de hand van de naam
     */
    public Wedstrijd laadWedstrijd(String naam) {
        Wedstrijd wedstrijd = new Wedstrijd();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Wedstrijd WHERE naam = ?");
            query.setString(1, naam);
            int aantalSets = 0;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    aantalSets = rs.getInt("aantalSets");
                }
            }
            
            PreparedStatement queryScoreSpeler1 = conn.prepareStatement("SELECT spelerID, score FROM ID222177_g14.Score WHERE naam = ?");
            queryScoreSpeler1.setString(1, naam);            
            int[] scoreSpelers = new int[2];
            int[] spelerIDSpelers = new int[2];
            int g = 0;
            try (ResultSet rs = queryScoreSpeler1.executeQuery()) {
                while (rs.next()) {                    
                    scoreSpelers[g] = rs.getInt("score");
                    spelerIDSpelers[g] = rs.getInt("spelerID");
                    g++;
                }
            }
                        
            PreparedStatement querySpelerNaam = conn.prepareStatement("SELECT gebruikersnaam FROM ID222177_g14.Speler WHERE spelerID =  ?");
            querySpelerNaam.setInt(1, spelerIDSpelers[0]);
            System.out.println(querySpelerNaam.toString());
            String naamSpeler1;            
            try (ResultSet rs = querySpelerNaam.executeQuery()) {
                rs.next();
                naamSpeler1 = rs.getString("gebruikersnaam");
            }
            querySpelerNaam.setInt(1, spelerIDSpelers[1]);
            String naamSpeler2;
            try (ResultSet rs = querySpelerNaam.executeQuery()) {
                rs.next();
                naamSpeler2 = rs.getString("gebruikersnaam");
            }
            
            PreparedStatement queryStapelSpeler1 = conn.prepareStatement("SELECT * FROM ID222177_g14.Wedstrijdstapel INNER JOIN ID222177_g14.Kaarttype WHERE naam = ?");
            queryStapelSpeler1.setString(1, naam);
            String omschrijvingSpeler1;
            List<Kaart> KaartenSpeler1 = new ArrayList<>();
            try (ResultSet rs = queryStapelSpeler1.executeQuery()) {
                while (rs.next()) {
                    omschrijvingSpeler1 = rs.getString("omschrijving");
                    String type = rs.getString("type");
                    String waarde = rs.getString("waarde");
                    int prijs = rs.getInt("prijs");
                    
                    KaartenSpeler1.add(new Kaart(omschrijvingSpeler1, type, waarde, prijs));           
                }
            }
            
            PreparedStatement queryStapelSpeler2 = conn.prepareStatement("SELECT * FROM ID222177_g14.Wedstrijdstapel INNER JOIN ID222177_g14.Kaarttype WHERE naam = ?");
            queryStapelSpeler2.setString(1, naam);
            String omschrijvingSpeler2;
            List<Kaart> KaartenSpeler2 = new ArrayList<>();
            try (ResultSet rs = queryStapelSpeler2.executeQuery()) {
                while (rs.next()) {                    
                    omschrijvingSpeler2 = rs.getString("omschrijving");
                    String type = rs.getString("type");
                    String waarde = rs.getString("waarde");
                    int prijs = rs.getInt("prijs");
                    
                    KaartenSpeler2.add(new Kaart(omschrijvingSpeler2, type, waarde, prijs));
                }
            }
            
            PreparedStatement querySpeler1 = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler WHERE spelerID = ?");
            querySpeler1.setInt(1, spelerIDSpelers[0]);
            Speler speler1 = null;
            try (ResultSet rs = querySpeler1.executeQuery()) {
                while (rs.next()) {
                    speler1 = new Speler(rs.getString("gebruikersnaam"), rs.getInt("geboortejaar"), rs.getDouble("krediet"));
                }
            }
            
            PreparedStatement querySpeler2 = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler WHERE spelerID = ?");
            querySpeler1.setInt(1, spelerIDSpelers[1]);
            Speler speler2 = null;
            try (ResultSet rs = querySpeler1.executeQuery()) {
                while (rs.next()) {
                    speler2 = new Speler(rs.getString("gebruikersnaam"), rs.getInt("geboortejaar"), rs.getDouble("krediet"));
                }
            }
            
            speler1.setSetScore(scoreSpelers[0]);
            speler2.setSetScore(scoreSpelers[1]);
            speler1.setWedstrijdStapel(KaartenSpeler1);
            speler2.setWedstrijdStapel(KaartenSpeler1);
            wedstrijd.registreerSpeler(speler1);
            wedstrijd.registreerSpeler(speler2);
            wedstrijd.setAantalSets(aantalSets);            
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return wedstrijd;
    }
}
