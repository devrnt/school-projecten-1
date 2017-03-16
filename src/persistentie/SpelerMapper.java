/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Speler;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class SpelerMapper {
    
    

    public void voegToe(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Speler (gebruikersnaam, krediet, geboortejaar)"
                    + "VALUES (?, ?, ?)");
            query.setString(1, speler.getGebruikersnaam());
            query.setDouble(2, speler.getKrediet());
            query.setInt(3, speler.getGeboortejaar());
            query.executeUpdate();
            

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    public boolean bestaatSpeler(String gebruikersnaam){
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler WHERE gebruikersnaam = ?");
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                return rs.next();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler WHERE gebruikersnaam = ?");
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naam = rs.getString("gebruikersnaam");
                    int geboortejaar = rs.getInt("geboortejaar");
                    double krediet = rs.getDouble("krediet");

                    speler = new Speler(naam, geboortejaar, krediet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }
    
    public List<String> geefLijstBeschikbareSpelers() {
        List<String> spelerLijst = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    spelerLijst.add(rs.getString("gebruikersnaam"));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelerLijst;
    }
    

}
