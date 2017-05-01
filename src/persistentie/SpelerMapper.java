/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kaart;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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

    public boolean bestaatSpeler(String gebruikersnaam) {
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

    /*public Speler geefSpeler(String gebruikersnaam) {
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
    }*/
    public List<Speler> geefLijstSpelers() {
        List<Speler> spelerLijst = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Speler");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    Speler tempSpeler = new Speler(rs.getString("gebruikersnaam"), rs.getInt("geboortejaar"), rs.getDouble("krediet"));
                    geefKaartenSpeler(tempSpeler, rs.getInt("spelerID"));
                    spelerLijst.add(tempSpeler);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return spelerLijst;
    }

    private void geefKaartenSpeler(Speler speler, int spelerID) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Kaart INNER JOIN ID222177_g14.Kaarttype USING (omschrijving) WHERE spelerID = ?");
            query.setInt(1, spelerID);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    speler.voegKaartToe(new Kaart(rs.getString("omschrijving"), rs.getString("type"), rs.getString("waarde"), rs.getInt("prijs")));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public void updateSpeler(Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            //updaten krediet
            PreparedStatement query = conn.prepareStatement("UPDATE ID222177_g14.Speler SET krediet = ?  WHERE gebruikersnaam = ?");
            query.setDouble(2, speler.getKrediet());
            query.setString(2, speler.getGebruikersnaam());
            query.executeQuery();

            //updaten gekochte kaarten
            List<Kaart> localKaarten = speler.getKaartLijst();
            List<String> serverKaarten = new ArrayList<>();
            PreparedStatement query2 = conn.prepareStatement("SELECT omschrijving FROM ID222177_g14.Kaart INNER JOIN ID222177_g14.Speler USING (gebruikersnaam) WHERE gebruikersnaam = ?");
            query2.setString(1, speler.getGebruikersnaam());
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    serverKaarten.add(rs.getString("omschrijving"));
                }
            }
            
            PreparedStatement idQuery = conn.prepareStatement("SELECT spelerID FROM ID222177_g14.Speler WHERE gebruikersnaam = ?");
            idQuery.setString(1, speler.getGebruikersnaam());
            int id;
            try (ResultSet rs = query.executeQuery()) {
                id = rs.getInt("spelerID");
            }
            
            for (Kaart kaart : localKaarten) {
                if (!serverKaarten.contains(kaart.getOmschrijving())) {
                    PreparedStatement insertStatement = conn.prepareStatement("INSERT INTO ID222177_g14.Kaart (spelerID, omschrijving)"
                        + "VALUES (?, ?)");
                    insertStatement.setInt(1, id);
                    insertStatement.setString(2, kaart.getOmschrijving());
                    insertStatement.executeUpdate();
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
