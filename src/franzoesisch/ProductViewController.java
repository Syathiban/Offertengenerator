/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void hinzufügen(ActionEvent event) {
        
        Database.getInstance().addProduct(txtFieldProduktName.getText(), Double.parseDouble(txtFieldStückpreis.getText()), txtFieldTyp.getText());
    }
    
}
