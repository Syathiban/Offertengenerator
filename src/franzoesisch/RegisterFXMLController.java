/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.Window;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class RegisterFXMLController implements Initializable {

    @FXML
    private ComboBox<String> sexuality;
    static String se;
    static String na;
    static String post;
    static Date dat;
    static String sur;
    static String city;
    static String rout;
    private boolean ok;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexuality.setItems(Geschlecht);
        try {
            se = sexuality.getSelectionModel().getSelectedItem();
            na = name.getText();
            post = plz.getText();
            //dat;
            rout = street.getText();
            sur = surname.getText();
            city = ort.getText();
            ok = true;
        } catch (Exception e) {
            ok = false;
        }
    }

    @FXML
    private void login(ActionEvent event) throws IOException {

        se = sexuality.getSelectionModel().getSelectedItem();
        na = name.getText();
        post = plz.getText();
        //dat;
        rout = street.getText();
        sur = surname.getText();
        city = ort.getText();
        ok = true;
        if (sexuality.getSelectionModel().getSelectedItem() == null || name.getText() == null || plz.getText() == null || street.getText() == null || surname.getText() == null || ort.getText() == null) {
            JOptionPane.showMessageDialog(null, "Alle Felder müssen ausgefüllt sein.");

        } else {
            if (ok = true) {
                if (se == "Männlich") {
                    se = "Monsieur";
                } else if (se == "Weiblich") {
                    se = "Madame";
                }
                Stage stage = Französisch.getStage();
                Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            } else {
                JOptionPane.showInternalMessageDialog(null, "Tous les champs doivent être remplis.");
            }
        }

    }

    static String getSe() {
        return se;
    }

    static String getNa() {
        return na;
    }

    static String getPost() {
        return post;
    }

    static String getCity() {
        return city;
    }

    public static String getSur() {
        return sur;
    }

    public static String getRout() {
        return rout;
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

}
