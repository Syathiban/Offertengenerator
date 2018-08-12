/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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
            this.connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/athiban_offerte", "root", "");
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

    public void Register(String vorname, String nachname, String geschlecht, int plz, String ort, String strasse, String email, String passwort) {
        try {
            String sql = "INSERT INTO newuser VALUES(null, ?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, vorname);
            prepstatement.setString(2, nachname);
            prepstatement.setString(3, geschlecht);
            prepstatement.setInt(4, plz);
            prepstatement.setString(5, ort);
            prepstatement.setString(6, strasse);
            prepstatement.setString(7, email);
            prepstatement.setString(8, passwort);
            
            prepstatement.executeUpdate();

        } catch (SQLException ex) {
            System.out.println("kgfhg");
        }

    }
    
    public boolean check(String email, String passwort) {

        if (email == null && passwort == null) {
            return false;
        }
        try {
            String user = "";
            String pw = "";

            String sql = "select email, passwort from newuser where email = ? and passwort = ?";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, email);
            prepstatement.setString(2, passwort);
            ResultSet result = prepstatement.executeQuery();
            result.next();
            user = result.getString("email");
            pw = result.getString("passwort");

            return true;

        } catch (SQLException ex) {
            return false;
        }
    }
    
    public void addProduct(String produktname, double preis, String typ) {
        try {
            String sql = "INSERT INTO produkte VALUES(null, ?, ?, ?)";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, produktname);
            prepstatement.setDouble(2, preis);
            prepstatement.setString(3, typ);
            prepstatement.executeUpdate();

        } catch (SQLException ex) {

        }

    }
    
      public ObservableList<String> getProdukte() {
        ObservableList<String> produkte = FXCollections.observableArrayList();
        try {
            String sql = "select * from produkte";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                produkte.add(rs.getString("produkt_name"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return produkte;
    }
      
        public void addKunde(String firmenname, String anrede, String name, String vorname, String addresszeile, String ort, int plz) {
        try {
            String sql = "INSERT INTO kunden VALUES(null, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, firmenname);
            prepstatement.setString(2, name);
            prepstatement.setString(3, vorname);
            prepstatement.setString(4, addresszeile);
            prepstatement.setInt(5, plz);
            prepstatement.setString(6, ort);
            prepstatement.setString(7, anrede);
            prepstatement.executeUpdate();

        } catch (SQLException ex) {

        }

    }
        
         public ResultSet getKunde(String firmenname) {
        try {
            String sql = "select * from kunden where firmenadresse = ?";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, firmenname);
            ResultSet rs = prepstatement.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         public ResultSet getUser(String vorname) {
        try {
            String sql = "select * from newuser where vorname = ?";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, vorname);
            ResultSet rs = prepstatement.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
         
             public ObservableList<String> getKunden() {
        ObservableList<String> kunden = FXCollections.observableArrayList();
        try {
            String sql = "select * from kunden";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                kunden.add(rs.getString("firmenadresse"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return kunden;
    }
             
              public ResultSet getProdukt(String produktname) {
        try {
            String sql = "select * from produkte where produkt_name = ?";
            PreparedStatement prepstatement = getConnection().prepareStatement(sql);
            prepstatement.setString(1, produktname);
            ResultSet rs = prepstatement.executeQuery();
            rs.next();
            return rs;
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
