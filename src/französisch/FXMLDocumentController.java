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
    
    private ObservableList<String> produkt = FXCollections.observableArrayList("Tablet", "PC", "Téléphone");
    private ObservableList<String> Zahlung = FXCollections.observableArrayList("Bank", "Post", "Bar");
    @FXML
    private Button submit;
    @FXML
    private void handleButtonAction(ActionEvent event) {
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        product.setItems(produkt);
        zahlung.setItems(Zahlung);
    }    
    
}
