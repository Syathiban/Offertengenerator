/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private TextField mail;
    @FXML
    private PasswordField password;
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private Pane topbar;
    boolean one = false;
    private Stage stage;
    String messa = "";
    String messa2 = "";
    String comparison = "english";
    @FXML
    private Label title;
    @FXML
    private Button confirm;
    private FXMLDocumentController con;
    @FXML
    private Label message;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        // TODO

        stage = Französisch.getStage();

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
        this.con = new FXMLDocumentController();
        this.con.setCon(this);
        String language = Database.getInstance().getLanguages();
        System.out.println(language + comparison);
        if (language.equals(comparison)) {
            title.setText("SIGN IN");
            mail.setPromptText("Email:");
            password.setPromptText("Password:");
            confirm.setText("Submit");
            messa = "Please enter your email address and password.";
            messa2 = "Please enter your correct email address and password.";
        } else {
            messa = "S'il vous plaît entrer votre nom d'utilisateur et mot de passe.";
            messa2 = "Veuillez entrer vos données correctes.";
        }
    }

    @FXML
    private void register(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("RegisterFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    @FXML
    private void submit(ActionEvent event) throws IOException {

        if (Database.getInstance().check(mail.getText().trim(), password.getText().trim())) {

            Stage stage = Französisch.getStage();
            Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();
            
        } else if (mail.getText().trim().isEmpty() || password.getText().trim().isEmpty()) {
            message.setText(messa);
        } else {
            message.setText(messa2);
        }

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

    public void changeLanguageEnglish(boolean one) {
        one = true;
//        Pcon.changeLanguageEnglish();
    }

    public void changeLanguageFrench(boolean one) {
        one = false;
//        Pcon.changeLanguageFrench();
    }

    @FXML
    private void changeLanguage(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("changeLanguage.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

}
