/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private ComboBox<String> sexuality;
    
    private String geburtstagsdatum;

    private ObservableList<String> Geschlecht = FXCollections.observableArrayList("Männlich", "Weiblich");
    @FXML
    private TextField name;
    @FXML
    private DatePicker date;
    @FXML
    private TextField plz;
    @FXML
    private TextField surname;
    @FXML
    private TextField ort;
    @FXML
    private TextField street;
    @FXML
    private TextField mail;
    @FXML
    private TextField password;
    
    private double xOffset = 0;

    private double yOffset = 0;
    @FXML
    private Pane topbar;

    private Stage stage;
    
    
    String vorname;
    String postalCode;
    String nachname;
    String city;
    String strasse;
    String email;
    String psw;

String gender;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        stage = Französisch.getStage();
        gender = sexuality.getSelectionModel().getSelectedItem();
        topbar.setOnMousePressed(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }

        });

        topbar.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setX(event.getScreenX() - xOffset);
                stage.setY(event.getScreenY() - yOffset);
            }
        });
        sexuality.setItems(Geschlecht);
        try {
            sexuality.getSelectionModel().getSelectedItem();
            name.getText();
            plz.getText();
            //dat;
            street.getText();
            surname.getText();
            ort.getText();

        } catch (Exception e) {

        }

        ObservableList<String> comboBoxFiller = FXCollections.observableArrayList();
        comboBoxFiller.addAll("Monsieur", "Madame");

        sexuality.setItems(comboBoxFiller);
    }

    @FXML
    private void login(ActionEvent event) throws IOException {
        gender = sexuality.getSelectionModel().getSelectedItem();
        vorname = name.getText();
        postalCode = plz.getText();
        nachname = surname.getText();
        city = ort.getText();
        strasse = street.getText();
        email = mail.getText();
        psw = password.getText();

//        LocalDate localGeburtstagsdatum = date.getValue();
//
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
//        geburtstagsdatum = localGeburtstagsdatum.format(formatter);
        
        int p_l_z = Integer.parseInt(plz.getText());
        if (surname.getText().trim().isEmpty() || name.getText().trim().isEmpty() || sexuality.getSelectionModel().getSelectedItem().trim().isEmpty() || plz.getText().trim().isEmpty() || ort.getText().trim().isEmpty()
                || street.getText().trim().isEmpty() || mail.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            System.out.println("Alle Felder müssen gefüllt sein.");
        } 
        System.out.println("muluk1");
        Database dat = new Database();
        dat.Register(surname.getText(), name.getText(), sexuality.getSelectionModel().getSelectedItem(), p_l_z, ort.getText(), street.getText(), mail.getText(), password.getText());
//        Database.getInstance().Register(surname.getText().trim(), name.getText().trim(), sexuality.getSelectionModel().getSelectedItem().trim(), p_l_z, ort.getText().trim(), 
//                street.getText().trim(), mail.getText().trim(), password.getText().trim());
    }

    @FXML
    private void back(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void close(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    private void minimize(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        stage.setIconified(true);
    }

    public String getGender() {
        return gender;
    }

    public String getVorname() {
        return vorname;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getNachname() {
        return nachname;
    }

    public String getCity() {
        return city;
    }

    public String getStrasse() {
        return strasse;
    }

    public String getEmail() {
        return email;
    }

    public String getPsw() {
        return psw;
    }

}
