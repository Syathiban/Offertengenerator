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
            this.connection = DriverManager.getConnection("jdbc:mysql://yannickhuggler.ddns.net:3306/athiban", "athiban", "HNbJK9MkLXevGvHtnNRC");
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

    public void Register(String vorname, String nachname, String anrede, String benutzername, String passwort) {
        try {
            String sql = "INSERT INTO newuser VALUES(null, ?, ?, ?, ?, ?)";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, vorname);
            prepstatement.setString(2, nachname);
            prepstatement.setString(3, anrede);
            prepstatement.setString(4, benutzername);
            prepstatement.setString(5, passwort);
            prepstatement.executeUpdate();

        } catch (SQLException ex) {

        }

    }

}
