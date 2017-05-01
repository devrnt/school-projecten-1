/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

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
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.Wedstrijd (gebruikersnaam, krediet, geboortejaar)"
                    + "VALUES (?, ?, ?)");

            query.executeUpdate();

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

}
