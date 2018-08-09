/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;
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

    private ObservableList<String> Zahlung = FXCollections.observableArrayList("Bank", "cheque Postal", "Bar");
    @FXML
    private Button submit;
    @FXML
    private TextField amount;
    @FXML
    private TextArea letter;
    int resultat;

    private double xOffset = 0;

    private String offertenText = "";

    private double yOffset = 0;
    @FXML
    private Pane topbar;
    boolean activated = false;
    private Stage stage;
    @FXML
    private Button btnMax;
    @FXML
    private Button btnClose;
    @FXML
    private Button btnMin;

    private String Firmenname = "<Firmenname>",
            Vorname = "<Vorname>",
            Nachname = "<Nachname>", Adresszeile_1 = "<Adresse>", Ort = "<Stadt>", Anrede = "<Anrede>";
    private int Postleitzahl;
    @FXML
    private ComboBox<String> Cliente;
    @FXML
    private Pane menu;
    @FXML
    private ImageView clientAdd;
    @FXML
    private ImageView productAdd;
    @FXML
    private ImageView logOut;
    @FXML
    private ImageView menuOpen;

    boolean activate = false;
    @FXML
    private Button english;
    @FXML
    private Button french;
    @FXML
    private Label title;

    private boolean one = false;
    @FXML
    private Pane menu2;
    public static FXMLDocumentController instance;
    LoginFXMLController Lcon;

    @FXML
    public void getInfo() throws SQLException {
        if (!Cliente.getSelectionModel().isEmpty()) {
            Firmenname = Cliente.getSelectionModel().getSelectedItem();
            ResultSet kunde = Database.getInstance().getKunde(Firmenname);

            Anrede = kunde.getString("anrede");

            Vorname = kunde.getString("vorname");
            Nachname = kunde.getString("nachname");
            Adresszeile_1 = kunde.getString("adresszeile");
            Ort = kunde.getString("ort");
            Postleitzahl = kunde.getInt("plz");
        }

    }

    public void CreateOfferText() {
        item = product.getSelectionModel().getSelectedItem();
        payment = zahlung.getSelectionModel().getSelectedItem();
        offertenText = (" VIN de Lausanne SA \n" + " 3, Rue de la Piquette \n 2000 Lausanne\n\n"
                + " " + Vorname + " " + Nachname + "\n"
                + " " + Adresszeile_1 + "\n"
                + " " + Postleitzahl + " " + Ort + "\n"
                + "\n"
                + "\n"
                + " " + Anrede + " " + Nachname + ",\n\n"
                + " Nous avons bien reçu votre commande du 30 mai et nous vous en remercions vivement.\n\n"
                + " Nous vous proposons " + amount.getText() + " " + item + " modèle 0815 au prix de " + resultat + " CHF par tablette.\n\n"
                + " De plus, nous vous offre une remise spéciale de 5% pour toute commande supérieure à 5000 CHF.\n\n"
                + " Nous vous demandons de faire le paiement dans les 30 jour sà notre compte de " + payment + " .\n\n"
                + " Nous allons faire la livraison par camion après la réception de votre paiement.\n\n"
                + " En vous remerciant d'avance de votre commande, nous vous prions d'agréer, Monsieur, nos distinguées.\n\n");
        letter.setEditable(false);

    }

    @FXML
    private void handleButtonAction(ActionEvent event) {

        CreateOfferText();
        letter.setText(offertenText);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        Cliente.setItems(Database.getInstance().getKunden());
        product.setItems(Database.getInstance().getProdukte());
        zahlung.setItems(Zahlung);

        CreateOfferText();

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

    @FXML
    private void DehoverClose(MouseEvent event) {
    }

    @FXML
    private void hoverClose(MouseEvent event) {

//          btnClose.setBackground(new Background(
//                new BackgroundImage(
//                        new Image("/Images/ic_launcher.png"),BackgroundRepeat.NO_REPEAT,
//                        BackgroundRepeat.NO_REPEAT,
//                        BackgroundPosition.CENTER,
//                        new BackgroundSize(
//                                17,17,
//                                false,false,true,true
//                        )
//                )
////        ));
    }

    private void addProduct(ActionEvent event) throws IOException {

        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("ProductView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    private void addClient(ActionEvent event) throws IOException {

        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("addCient.fxml"));
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
    private void openMenu(MouseEvent event) {

        if (activated == true) {
            menu.setOpacity(0);
            clientAdd.setOpacity(0);
            productAdd.setOpacity(0);
            logOut.setOpacity(0);
            activated = false;
        } else {
            menu2.setOpacity(0);
            french.setOpacity(0);
            english.setOpacity(0);
            activate = false;
            menu.setOpacity(1);
            clientAdd.setOpacity(1);
            productAdd.setOpacity(1);
            logOut.setOpacity(1);
            activated = true;
        }
    }

    private void changeEnglish(ActionEvent event) {
        System.out.println("muluk");
        submit.setText("Submit");
        title.setText("Create an Offer");
        product.setPromptText("Product");
        amount.setPromptText("Amount");
        zahlung.setPromptText("Payment methode");
        Cliente.setPromptText("Client");
        french.setText("French");
        english.setText("English");

//        Lcon.changeLanguageEnglish();
    }

    @FXML
    private void changeFrench(ActionEvent event) {
        System.out.println("muluk");
        submit.setText("confirmer");
        title.setText("Créer un offre");
        product.setPromptText("produit");
        amount.setPromptText("nombre de pièces");
        zahlung.setPromptText("mode de paiement");
        Cliente.setPromptText("client");
        french.setText("Français");
        english.setText("Anglais");
        one = false;
    }

    @FXML
    private void hide(MouseEvent event) {
        if (activate == false) {
            menu.setOpacity(0);
            clientAdd.setOpacity(0);
            productAdd.setOpacity(0);
            logOut.setOpacity(0);
            activated = false;
            menu2.setOpacity(1);
            french.setOpacity(1);
            english.setOpacity(1);
            activate = true;
        } else {
            menu2.setOpacity(0);
            french.setOpacity(0);
            english.setOpacity(0);
            activate = false;
        }

    }

    public void setCon(LoginFXMLController Lcon) {
        this.Lcon = Lcon;
    }

    @FXML
    private void changeEnglish(MouseEvent event) {
        System.out.println("muluk45");
        submit.setText("Submit");
        title.setText("Create an Offer");
        product.setPromptText("Product");
        amount.setPromptText("Amount");
        zahlung.setPromptText("Payment methode");
        Cliente.setPromptText("Client");
        french.setText("French");
        english.setText("English");
        one = true;
        isOne(one);
    }

    public boolean isOne(boolean one) {
        return one;
    }
    
    
}
