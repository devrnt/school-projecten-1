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
import java.sql.SQLException;

/**
 *
 * @author Jonas
 */
public class KaartMapper {
    
        public void voegToe(Kaart kaart, Speler speler) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL)) {
            PreparedStatement query = conn.prepareStatement("INSERT INTO ID222177_g14.kaarttype (omschrijving, waarde, type)"
                    + "VALUES (?, ?, ?)");
            query.setString(1, kaart.getOmschrijving());
            query.setDouble(2, kaart.getWaarde());
            query.setString(3, kaart.getType().split("/")[0] + kaart.getType().split("/")[1]);
            query.executeUpdate();
            

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }
    
}
