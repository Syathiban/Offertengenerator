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
import javafx.scene.control.Label;
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

    String comparison = "english";
    private Stage stage;

    String vorname;
    String postalCode;
    String nachname;
    String city;
    String strasse;
    String email;
    String psw;
    String male;
    String female;
    int p_l_z;

    String gender;
    @FXML
    private Button confirm;
    @FXML
    private Label title;
    @FXML
    private Label message;
    String messa = "";

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
//        sexuality.setItems(Geschlecht);
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
        String language = Database.getInstance().getLanguages();
        System.out.println(language + comparison);
        if (language.equals(comparison)) {
            title.setText("Register");
            surname.setPromptText("Firstname:");
            name.setPromptText("Lastname:");
            sexuality.setPromptText("Salutation");
            plz.setPromptText("Postal code:");
            ort.setPromptText("City:");
            street.setPromptText("Street:");
            date.setPromptText("Date");
            mail.setPromptText("Email:");
            password.setPromptText("Password:");
            confirm.setText("Submit");
            messa = "All fields must be filled in.";
            male = "Mister";
            female = "Madame";
        } else {
            messa = "Tous les champs sont obligatoires.";
            male = "Monsieur";
            female = "Madame";
        }
        ObservableList<String> comboBoxFiller = FXCollections.observableArrayList();
        comboBoxFiller.addAll(male, female);

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
        try {
            p_l_z = Integer.parseInt(plz.getText());
        } catch (Exception e) {
            p_l_z = 0;
        }
        
        if (p_l_z == 0 || surname.getText().trim().isEmpty() || name.getText().trim().isEmpty() || sexuality.getSelectionModel().getSelectedItem().trim().isEmpty() || plz.getText().trim().isEmpty() || ort.getText().trim().isEmpty()
                || street.getText().trim().isEmpty() || mail.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            message.setText(messa);
        }else if(p_l_z != 0){
            Database dat = new Database();
            dat.Register(surname.getText(), name.getText(), sexuality.getSelectionModel().getSelectedItem(), p_l_z, ort.getText(), street.getText(), mail.getText(), password.getText());
//        Database.getInstance().Register(surname.getText().trim(), name.getText().trim(), sexuality.getSelectionModel().getSelectedItem().trim(), p_l_z, ort.getText().trim(), 
//                street.getText().trim(), mail.getText().trim(), password.getText().trim());
            Stage stage = Französisch.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();  
        }
        
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
        Database.getInstance().editLanguage();
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

    public void changeLanguageEnglish() {
        title.setText("Register");
        surname.setPromptText("Firstname:");
        name.setPromptText("Lastname:");
        sexuality.setPromptText("Gender");
        plz.setPromptText("Postal code:");
        ort.setPromptText("City:");
        street.setPromptText("Street:");
        date.setPromptText("Date");
        mail.setPromptText("Email:");
        password.setPromptText("Password:");
        confirm.setText("Submit");
    }

    public void changeLanguageFrench() {
        title.setText("Enregistrer");
        surname.setPromptText("prénom:");
        name.setPromptText("nom de famille:");
        sexuality.setPromptText("sexe");
        plz.setPromptText("code postal:");
        ort.setPromptText("localité:");
        street.setPromptText("rue:");
        date.setPromptText("date");
        mail.setPromptText("email:");
        password.setPromptText("mot de passe:");
        confirm.setText("Confirmer");
    }

}
