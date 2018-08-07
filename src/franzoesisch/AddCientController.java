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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void adden(ActionEvent event) {
        
        Database.getInstance().addKunde(txtFieldFirmenname.getText(), txtFieldAnrede.getText(), txtFieldNachname.getText(), txtFieldVorname.getText()
                , txtFieldAdresse.getText(), txtFieldOrt.getText(), Integer.parseInt(txtFieldPlz.getText()));
    }
    
}
