/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package französisch;

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
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;
import javafx.stage.Window;

/**
 * FXML Controller class
 *
 * @author Startklar
 */
public class LoginFXMLController implements Initializable {

    @FXML
    private ComboBox<String> sexuality;

    private ObservableList<String> Geschlecht = FXCollections.observableArrayList("Männlich", "Weiblich", "Anderes");

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sexuality.setItems(Geschlecht);
    }

    @FXML
    private void login(ActionEvent event) {
        
        Window window = Französisch.getStage();
        root = FXMLLoader.load(getClass().getResource("authentication.fxml"));
        window.initStyle(StageStyle.UNDECORATED);
        window.setTitle("Markalyzer");
        window.setScene(new Scene(root));
        window.show();

    }

}
