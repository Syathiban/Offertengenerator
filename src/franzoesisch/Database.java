/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Startklar
 */
public class Database {

    private static Database instance = null;
    private Connection connection;
    private Statement statement;

    public Database() {
        try {
            //Verbindung zum Server herstellen
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost/athiban", "root", "");
            this.statement = connection.createStatement();
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    public static Database getInstance() {
        if (instance == null) {
            instance = new Database();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void Register(String vorname, String nachname, String anrede, String benutzername, String passwort, int plz, String adresse, String city, String email) {
        try {
            String sql = "INSERT INTO newuser VALUES(null, ?, ?, ?, ?, ?)";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(2, vorname);
            prepstatement.setString(3, nachname);
            prepstatement.setString(4, anrede);
            prepstatement.setString(5, benutzername);
            prepstatement.setString(6, email);
            prepstatement.setString(7, passwort);
            prepstatement.setString(8, adresse);
            prepstatement.setString(9, city);
            prepstatement.setInt(10, plz);
            
            prepstatement.executeUpdate();

        } catch (SQLException ex) {

        }

    }

}
