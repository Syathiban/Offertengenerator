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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 *
 * @author Startklar
 */
public class FXMLDocumentController implements Initializable {
    
    private Label label;
    @FXML
    private ComboBox<String> product;
    @FXML
    private ComboBox<String> zahlung;
    String item;
    String payment;
    private ObservableList<String> produkt = FXCollections.observableArrayList("tablettes", "PC", "téléphone");
    private ObservableList<String> Zahlung = FXCollections.observableArrayList("Bank", "cheque Postal", "Bar");
    @FXML
    private Button submit;
    @FXML
    private TextField amount;
    @FXML
    private TextArea letter;
     int resultat;
    @FXML
    private ImageView lout;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        int am = Integer.parseInt(amount.getText());
        //Calculation price
        
        
        
        item = product.getSelectionModel().getSelectedItem();
        payment = zahlung.getSelectionModel().getSelectedItem();
        letter.setText(" VIN de Lausanne SA \n" + " 3, Rue de la Piquette \n 2000 Lausanne\n\n"
                + " " + RegisterFXMLController.getNa() + " " + RegisterFXMLController.getSur() + "\n"
                + " " + RegisterFXMLController.getRout() + "\n"
                + " " + RegisterFXMLController.getPost() + " " + RegisterFXMLController.getCity() + "\n"
                + "\n"
                + "\n"
                + " " + RegisterFXMLController.getSe()+ " " + RegisterFXMLController.getNa() + ",\n\n"
                + " Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement.\n\n"
                + " Nous vous proposons " + amount.getText()+ " " + item + " modèle 0815 au prix de " + resultat + " CHF par tablette.\n\n"
                + " De plus, nous vous offre une remise spéciale de 5% pour toute commande supérieure à 5000 CHF.\n\n"
                + " Nous vous demandons de faire le paiement dans les 30 jour sà notre compte de " + payment + " .\n\n"
                + " Nous allons faire la livraison par camion après la réception de votre paiement.\n\n"
                + " En vous remerciant d'avance de votre commande, nous vous prions d'agréer, Monsieur, nos distinguées.\n\n" );
        letter.setEditable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        product.setItems(produkt);
        zahlung.setItems(Zahlung);
    }    

    @FXML
    private void logout(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }
    
}
