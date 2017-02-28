/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Speler;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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

}
