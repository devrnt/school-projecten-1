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

/**
 *
 * @author Jonas
 */
public class SpelerMapper {

    public void voegToe(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.speler (gebruikersnaam, krediet, geboortejaar)"
                    + "VALUES (?, ?, ?)");
            query.setString(1, speler.getGebruikersnaam());
            query.setDouble(2, speler.getKrediet());
            query.setInt(3, speler.getGeboortejaar());
            query.executeUpdate();
            

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
    
    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.speler WHERE gebruikersnaam = ?");
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String naam = rs.getString("naam");
                    String voornaam = rs.getString("voornaam");
                    LocalDate geboortedatum = rs.getDate("geboortedatum").toLocalDate();
                    String wachtwoord = rs.getString("wachtwoord");
                    boolean beheerder = rs.getBoolean("beheerder");
                    BigDecimal krediet = rs.getBigDecimal("krediet");

                    speler = new Speler(naam, voornaam, emailadres, geboortedatum, wachtwoord, beheerder, krediet);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return speler;
    }
    

}
