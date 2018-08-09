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
public class ProductViewController implements Initializable {

    @FXML
    private TextField txtFieldProduktName;
    @FXML
    private TextField txtFieldStückpreis;
    @FXML
    private TextField txtFieldTyp;
    @FXML
    private Pane menu;
    @FXML
    private ImageView clientAdd;
    @FXML
    private ImageView productAdd;
    @FXML
    private ImageView logOut;
    @FXML
    private Pane topbar;
    @FXML
    private ImageView returnView;
    @FXML
    private ImageView menuOpen;

    private boolean activated = false;
    @FXML
    private Label message;
    @FXML
    private Button confirm;
    @FXML
    private Label title;

    private double xOffset = 0;

    private double yOffset = 0;
    private Stage stage;
    AddCientController Acon = new AddCientController();

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
    private void hinzufügen(ActionEvent event) {

        Database.getInstance().addProduct(txtFieldProduktName.getText(), Double.parseDouble(txtFieldStückpreis.getText()), txtFieldTyp.getText());
        message.setText("Le produit a été ajouté.");
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
        Parent root = FXMLLoader.load(getClass().getResource("addCient.fxml"));
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
            activated = false;
        } else {
            menu.setOpacity(1);
            clientAdd.setOpacity(1);
            productAdd.setOpacity(1);
            logOut.setOpacity(1);
            activated = true;
        }

    }
//    public void changeLanguageEnglish(){
//        title.setText("Add product");
//        txtFieldStückpreis.setPromptText("Price per unit");
//        txtFieldProduktName.setPromptText("Product name:");
//        txtFieldTyp.setPromptText("Type:");
//        confirm.setText("Add");
//        Acon.changeLanguageEnglish();
//    }
//    
//    public void changeLanguageFrench(){
//        title.setText("Ajouter un produit");
//        txtFieldStückpreis.setPromptText("prix à l'unité:");
//        txtFieldProduktName.setPromptText("nom de produit:");
//        txtFieldTyp.setPromptText("modèle:");
//        confirm.setText("Ajouter");
//        Acon.changeLanguageFrench();
//    }

}
