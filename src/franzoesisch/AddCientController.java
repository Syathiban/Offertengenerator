/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class AddCientController implements Initializable {

    @FXML
    private TextField txtFieldFirmenname;
    @FXML
    private TextField txtFieldAnrede;
    @FXML
    private TextField txtFieldNachname;
    @FXML
    private TextField txtFieldVorname;
    @FXML
    private TextField txtFieldAdresse;
    @FXML
    private TextField txtFieldPlz;
    @FXML
    private TextField txtFieldOrt;
    @FXML
    private Pane topbar;
    @FXML
    private ImageView returnView;
    @FXML
    private ImageView menuOpen;
    @FXML
    private Pane menu;
    @FXML
    private ImageView clientAdd;
    @FXML
    private ImageView productAdd;
    @FXML
    private ImageView logOut;

    private boolean activated = false;
    @FXML
    private Label message;
    @FXML
    private Button confirm;
    @FXML
    private Label title;

    private double xOffset = 0;

    private double yOffset = 0;
    boolean one = false;
    private Stage stage;

    RegisterFXMLController Rcon = new RegisterFXMLController();
    @FXML
    private ImageView mitarbeiter;
    @FXML
    private ImageView clAddback;
    @FXML
    private Label clAdd;
    @FXML
    private ImageView plAddBack;
    @FXML
    private Label plAdd;
    @FXML
    private ImageView arAddBack;
    @FXML
    private Label arAdd;
    @FXML
    private ImageView logOutBack;
    @FXML
    private Label logout;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Stage stage = Französisch.getStage();

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
    }

    @FXML
    private void adden(ActionEvent event) {

        Database.getInstance().addKunde(txtFieldFirmenname.getText(), txtFieldAnrede.getText(), txtFieldNachname.getText(), txtFieldVorname.getText(),
                 txtFieldAdresse.getText(), txtFieldOrt.getText(), Integer.parseInt(txtFieldPlz.getText()));
        message.setText("Le client a été ajouté.");
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

    private void logout(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void cAdd(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("addClient.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void pAdd(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void logOut(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void rView(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    @FXML
    private void openMenu(MouseEvent event) {
        if (activated == true) {
            menu.setOpacity(0);
            clientAdd.setOpacity(0);
            productAdd.setOpacity(0);
            logOut.setOpacity(0);
            mitarbeiter.setOpacity(0);
            activated = false;
        } else {
            menu.setOpacity(1);
            clientAdd.setOpacity(1);
            productAdd.setOpacity(1);
            logOut.setOpacity(1);
            mitarbeiter.setOpacity(1);
            activated = true;
        }
    }
//    public void changeLanguageEnglish(){
//        title.setText("Add Client");
//        txtFieldNachname.setPromptText("Lastname:");
//        txtFieldVorname.setPromptText("Firstname:");
//        txtFieldFirmenname.setPromptText("Company name:");
//        txtFieldAnrede.setPromptText("Salutation:");
//        txtFieldAdresse.setPromptText("Addresse:");
//        txtFieldPlz.setPromptText("Postal code:");
//        txtFieldOrt.setPromptText("City:");
//        confirm.setText("Add");
//        Rcon.changeLanguageEnglish();
//    }
//    
//    public void changeLanguageFrench(){
//        title.setText("Ajouter un client");
//        txtFieldNachname.setPromptText("nom de famille:");
//        txtFieldVorname.setPromptText("prénom:");
//        txtFieldFirmenname.setPromptText("nom de l'enterprise:");
//        txtFieldAnrede.setPromptText("titre:");
//        txtFieldAdresse.setPromptText("adresse:");
//        txtFieldPlz.setPromptText("code postal:");
//        txtFieldOrt.setPromptText("localité:");
//        confirm.setText("Ajouter");
//        Rcon.changeLanguageFrench();
//    }
    @FXML
    private void addMitarbeiter(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("addMitarbeiter.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }
    
    @FXML
    private void addClientExit(MouseEvent event) {
        clAdd.setOpacity(0);
        clAddback.setOpacity(0);
    }

    @FXML
    private void addClientEnter(MouseEvent event) {
        if (activated == true) {
            clAddback.setOpacity(1);
            clAdd.setOpacity(1);
            clAdd.setText("Ajouter un client");
        }
        
    }

    @FXML
    private void addProduitExit(MouseEvent event) {
        plAddBack.setOpacity(0);
        plAdd.setOpacity(0);
    }

    @FXML
    private void addProduitEnter(MouseEvent event) {
        if (activated == true) {
            plAddBack.setOpacity(1);
            plAdd.setOpacity(1); 
        }
        
    }

    @FXML
    private void logOutExit(MouseEvent event) {
        logOutBack.setOpacity(0);
        logout.setOpacity(0);
    }

    @FXML
    private void logOutEnter(MouseEvent event) {
        if (activated == true) {
            logOutBack.setOpacity(1);
            logout.setOpacity(1); 
        }
        
    }

    @FXML
    private void addArbeiterExit(MouseEvent event) {
        arAddBack.setOpacity(0);
        arAdd.setOpacity(0);
    }

    @FXML
    private void addArbeiterEnter(MouseEvent event) {
        if (activated == true) {
            arAddBack.setOpacity(1);
            arAdd.setOpacity(1);
        }
        
    }

}
