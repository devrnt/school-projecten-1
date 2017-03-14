/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Kaart;
import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Jonas
 */
public class KaartMapper {

    public void voegToe(Kaart kaart, Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Kaarttype (omschrijving, waarde, type)"
                    + "VALUES (?, ?, ?)");
            query.setString(1, kaart.getOmschrijving());
            query.setDouble(2, kaart.getWaarde());
            query.setString(3, kaart.getType().split("/")[0] + kaart.getType().split("/")[1]);
            query.executeUpdate();

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public List<Kaart> getStartKaarten() {
        List<Kaart> startKaarten = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("SELECT * FROM ID222177_g14.Kaarttype");
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String omschrijving = rs.getString("omschrijving");
                    int waarde = rs.getInt("waarde");
                    String type = rs.getString("type");

                    startKaarten.add(new Kaart(omschrijving, type, waarde));
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return startKaarten;
    }

    //kan in feite samengevoegd worden met de methode die startkaarten opvraagt
    public void voegStartkaartenToe(Speler speler, List<Kaart> startKaarten) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement spelerlijst = conn.prepareStatement("SELECT * FROM ID222177_dobbel.Speler WHERE gebruikersnaam = ?");
            spelerlijst.setString(1, speler.getGebruikersnaam());
            ResultSet rs = spelerlijst.executeQuery();
            int spelerId = rs.getInt("spelerID");

            for (Kaart kaart : startKaarten) {
                PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Kaart (spelerid, omschrijving)"
                        + "VALUES (?, ?)");
                query.setInt(1, spelerId);
                query.setString(2, kaart.getOmschrijving());
                query.executeUpdate();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
