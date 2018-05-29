/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package französisch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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

    @FXML
    private void handleButtonAction(ActionEvent event) {
        item = product.getSelectionModel().getSelectedItem();
        payment = zahlung.getSelectionModel().getSelectedItem();
        letter.setText("Monsieur, \n"
                + "Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement.\n"
                + "Nous vous proposons 10 " + item + " modèle 0815 au prix de 800 CHF par tablette.\nDe plus, nous vous offre une remise spéciale de 5% pour toute commande supérieure à 5000 CHF.\n"
                + "Nous vous demandons de faire le paiement dans les 30 jour sà notre compte de " + payment + " .\n"
                + "Nous allons faire la livraison par camion après la réception de votre paiement.\n"
                + "En vous remerciant d'avance de votre commande, nous vous prions d'agréer, Monsieur, nos distinguées.\n" );
        letter.setEditable(false);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        product.setItems(produkt);
        zahlung.setItems(Zahlung);
    }    
    
}
