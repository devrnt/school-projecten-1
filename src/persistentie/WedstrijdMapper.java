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

    public void bewaarWedstrijd(String naam, Wedstrijd wedstrijd) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijd (naam, aantalSets)"
                    + "VALUES (?, ?)");

            query.setString(1, naam);
            query.setInt(2, wedstrijd.getAantalSets());
            query.executeQuery();

            PreparedStatement querySpelerID = conn.prepareStatement("SELECT spelerID FROM ID222177_g14.Speler WHERE gebruikersnaam = ?");
            querySpelerID.setString(1, wedstrijd.getSpeler1().getGebruikersnaam());
            int id1;
            try (ResultSet rs = querySpelerID.executeQuery()) {
                id1 = rs.getInt("spelerID");
            }
            querySpelerID.setString(1, wedstrijd.getSpeler2().getGebruikersnaam());
            int id2;
            try (ResultSet rs = querySpelerID.executeQuery()) {
                id2 = rs.getInt("spelerID");
            }

            PreparedStatement querySpeler1 = conn.prepareStatement("INSERT INTO ID222177_g14.Score (spelerID, naam, score)"
                    + "VALUES (?, ?, ?)");
            querySpeler1.setInt(1, id1);
            querySpeler1.setString(2, wedstrijd.getSpeler1().getGebruikersnaam());
            querySpeler1.setInt(3, wedstrijd.getSpeler1().getSetScore());
            querySpeler1.executeQuery();

            PreparedStatement querySpeler2 = conn.prepareStatement("INSERT INTO ID222177_g14.Score (spelerID, naam, score)"
                    + "VALUES (?, ?, ?)");
            querySpeler2.setInt(1, id2);
            querySpeler2.setString(2, wedstrijd.getSpeler2().getGebruikersnaam());
            querySpeler2.setInt(3, wedstrijd.getSpeler2().getSetScore());
            querySpeler2.executeQuery();

            PreparedStatement queryStapelSpeler1 = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijdstapel (spelerID, naam, omschrijving)"
                    + "VALUES (?, ?, ?)");
            queryStapelSpeler1.setInt(1, id1);
            queryStapelSpeler1.setString(2, wedstrijd.getSpeler1().getGebruikersnaam());
            for (Kaart kaart : wedstrijd.getSpeler1().getWedstrijdStapel()) {
                queryStapelSpeler1.setString(3, kaart.getOmschrijving());
                queryStapelSpeler1.executeQuery();                
            }
            PreparedStatement queryStapelSpeler2 = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijdstapel (spelerID, naam, omschrijving)"
                    + "VALUES (?, ?, ?)");
            queryStapelSpeler2.setInt(1, id2);
            queryStapelSpeler2.setString(2, wedstrijd.getSpeler2().getGebruikersnaam());
            for (Kaart kaart : wedstrijd.getSpeler2().getWedstrijdStapel()) {
                queryStapelSpeler2.setString(3, kaart.getOmschrijving());
                queryStapelSpeler2.executeQuery();
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

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

    public Wedstrijd laadWedstrijd(String naam) {
        Wedstrijd wedstrijd = new Wedstrijd();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Wedstrijd WHERE naam = ?");
            query.setString(1, naam);
            int aantalSets;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    aantalSets = rs.getInt("aantalSets");
                }
            }
            
            PreparedStatement queryScoreSpeler1 = conn.prepareStatement("SELECT score FROM ID222177_g14.Score WHERE naam = ?");
            queryScoreSpeler1.setString(1, naam);            
            int scoreSpeler1;
            int spelerIDSpeler1 = 0;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    scoreSpeler1 = rs.getInt("score");
                    spelerIDSpeler1 = rs.getInt("spelerID");
                }
            }
            
            PreparedStatement queryScoreSpeler2 = conn.prepareStatement("SELECT spelerID, score FROM ID222177_g14.Score WHERE naam = ?");
            queryScoreSpeler2.setString(1, naam);            
            int scoreSpeler2;
            int spelerIDSpeler2 = 0;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    scoreSpeler2 = rs.getInt("score");
                    spelerIDSpeler2 = rs.getInt("spelerID");
                }
            }
            
            PreparedStatement querySpelerNaam = conn.prepareStatement("SELECT gebruikersnaam FROM ID222177_g14.Speler WHERE spelerID =  ?");
            querySpelerNaam.setInt(1, spelerIDSpeler1);
            String naamSpeler1;            
            try (ResultSet rs = querySpelerNaam.executeQuery()) {
                naamSpeler1 = rs.getString("gebruikersnaam");
            }
            querySpelerNaam.setInt(1, spelerIDSpeler2);
            String naamSpeler2;
            try (ResultSet rs = querySpelerNaam.executeQuery()) {
                naamSpeler2 = rs.getString("gebruikersnaam");
            }
            
            PreparedStatement queryStapelSpeler1 = conn.prepareStatement("SELECT omschrijving FROM ID222177_g14.Wedstrijdstapel WHERE naam = ?");
            queryScoreSpeler1.setString(1, naam);
            String omschrijvingSpeler1;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                 
                    omschrijvingSpeler1 = rs.getString("omschrijving");
                }
            }
            
            PreparedStatement queryStapelSpeler2 = conn.prepareStatement("SELECT omschrijving FROM ID222177_g14.Wedstrijdstapel WHERE naam = ?");
            queryStapelSpeler2.setString(1, naam);
            String omschrijvingSpeler2;
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {                    
                    omschrijvingSpeler2 = rs.getString("omschrijving");
                }
            } 
      
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return wedstrijd;
    }
}
