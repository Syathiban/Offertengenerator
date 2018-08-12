/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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

    private ObservableList<String> Zahlung = FXCollections.observableArrayList("virement bancaire", "cheque Postal", "paiement en espèces");
    @FXML
    private Button submit;
    @FXML
    private TextField amount;
    @FXML
    private TextArea letter;
    int resultat;
    private String offertenTextPDF = "";
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
            Nachname = "<Nachname>", Adresszeile_1 = "<Adresse>", Ort = "<Stadt>", Anrede = "<Anrede>", automatischesDatum, Produktname, Termin1 = "<Termin1>";

    private double Einzelpreis, Totalpreis, Rabatt;
    private int Postleitzahl, Anzahl;
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
    private Button english;
    private Button french;
    @FXML
    private Label title;

    private int plz, aaa;
    String street, ort;

    private boolean one = false;
    public static FXMLDocumentController instance;
    LoginFXMLController Lcon;
    @FXML
    private DatePicker dateEmpfangsdatum;

    String Aktionshinweis, Beraterhinweis, Verkäufer, Position, bbb;

    int Zahlungsfrist = 30;
    Double MWST = 7.7, Rabattmenge;
    @FXML
    private ImageView mitarbeiter;
    @FXML
    private ComboBox<String> arbeiter;
    @FXML
    private ImageView download;
    @FXML
    private Label mess;

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

            Produktname = product.getSelectionModel().getSelectedItem();
            aaa = Integer.parseInt(amount.getText());
            ResultSet produkt = Database.getInstance().getProdukt(Produktname);
            Einzelpreis = produkt.getDouble("stückpreis");
            Einzelpreis = produkt.getDouble("stückpreis");

            Totalpreis = Einzelpreis * aaa;

            if (dateEmpfangsdatum.getValue() != null) {
                LocalDate ld = dateEmpfangsdatum.getValue();
                Calendar c = Calendar.getInstance();
                c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
                Date date = c.getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");

                Termin1 = dateFormat.format(date);
            }
            Verkäufer = arbeiter.getSelectionModel().getSelectedItem();
            
        }

    }

    public void CreateOfferText() {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        Date date = new Date();
        automatischesDatum = dateFormat.format(date);

        double bbb = 10;
        double res = Totalpreis / bbb;
        Rabattmenge = Totalpreis - res;

        item = product.getSelectionModel().getSelectedItem();
        payment = zahlung.getSelectionModel().getSelectedItem();
        offertenText = (" VIN de Lausanne SA \n" + " 3, Rue de la Piquette \n 2000 Lausanne\n\n"
                + " " + Firmenname + "\n " + Nachname + " " + Vorname + "\n " + Adresszeile_1 + "\n " + Postleitzahl + " " + Ort + "\n\n\n\n"
                + "Lausanne, " + automatischesDatum + "\n\n\n"
                + "Offre pour " + Produktname + "\n"
                + "\n\n"
                + "\n"
                + "" + Anrede + " " + Nachname + ",\n\n"
                + "Nous avons bien reçu votre demande du" + " " + Termin1 + "  " + "et nous vous en remercions vivement." + "\n"
                + "Nous avons le plaisir de soumettre l'offre suivante." + "\n\n"
                + "  " + aaa + "      " + Produktname + "      " + Totalpreis + " CHF" + "      " + MWST + "%\n\n"
                + "Nous vous proposons le " + Produktname + " au prix de " + Einzelpreis + " CHF chacun, y compris " + MWST + "% de TVA .\n"
                + "En outre, vous pouvez profiter d’une remise spéciale de " + bbb + " % pour toute commande" + "  supérieure à " + Rabattmenge + " CHF.\n"
                + "Nous promettons de vous livrer la commande sous 7 jours." + "\n" + "Le délai de paiement est de " + Zahlungsfrist + " jours" + " " + "après réception de la marchandise." + "\n"
                + "Nous vous prions de payer " + payment + " Cette offre est valable" + " " + "jusqu'au " + Termin1 + "." + "\n\n  "
                + Aktionshinweis
                + Beraterhinweis
                + "" + " Si vous avec encore des questions, n'hésitez pas de nous contacter.\n"
                + "Dans l’attente de votre commande, nous vous prions d'agréer " + Anrede + " nos meilleures salutations. \n\n"
                
                + Verkäufer);
        letter.setEditable(false);

    }

    public String getHTMLText() {

        String aaa = String.valueOf(Anzahl);
        //aaa = "<Anzahl>";

        String bbb = String.valueOf(Rabatt);
        //bbb = "<Rabatt>";


        offertenTextPDF += "   VIN de Lausanne SA <br>" + "   3, Rue de la Piquette <br>   2000 Lausanne<br><br>";
        offertenTextPDF += "  " + Firmenname + "<br>  " + Nachname + " " + Vorname + "<br>  " + Adresszeile_1 + "<br>  " + Postleitzahl + " " + Ort + "<br><br><br><br>";
        offertenTextPDF += "  Lausanne, " + automatischesDatum + "<br><br>";
        offertenTextPDF += "  Offre pour " + Produktname + "<br><br>";
        offertenTextPDF += "  " + Anrede + " " + Nachname + " <br><br>";

        offertenTextPDF += "  Nous avons bien reçu votre demande du" + " " + Termin1 + "  " + "et nous vous en remercions vivement." + "<br>";
        offertenTextPDF += "  Nous avons le plaisir de soumettre l'offre suivante." + "<br><br>";

        offertenTextPDF += "  " + aaa + "      " + Produktname + "      " + Totalpreis + " CHF" + "      " + MWST + "<br><br>";

        offertenTextPDF += "   Nous vous proposons le " + Produktname + " au prix de " + Einzelpreis + " CHF chacun, y compris " + MWST + "% de TVA « »." + "<br>"
                + "  En outre, vous pouvez profiter d’une remise spéciale de " + bbb + " % pour toute commande" + "<br>" + "  supérieure à " + Rabattmenge + " CHF." + "<br><br>";

        offertenTextPDF += "  Nous promettons de vous livrer la commande sous 7 jours." + "\n" + "  Le délai de paiement est de " + Zahlungsfrist + " jours" + " " + "après réception de la marchandise." + "\n"
                + "  Nous vous prions de payer " + payment + " Cette offre est valable" + " " + "jusqu'au " + Termin1 + "." + "\n\n  ";

        offertenTextPDF += Aktionshinweis + "<br><br>  ";
        offertenTextPDF += Beraterhinweis;

        offertenTextPDF += "" + " Si vous avec encore des questions, n'hésitez pas de nous contacter." + "<br><br>  ";
        offertenTextPDF += "  Dans l’attente de votre commande, nous vous prions d'agréer " + Anrede + " nos meilleures salutations." + "<br><br>  ";

        offertenTextPDF += Verkäufer;
        return offertenTextPDF;
    }

    @FXML
    private void generatePDF(MouseEvent event) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream("Texte d'offre.pdf"));

        document.open();
        
        HTMLWorker hw = new HTMLWorker(document);
        hw.parse(new StringReader("<html><p>" + this.getHTMLText().concat(offertenTextPDF) +"</p></html>"));
        document.close();
        
        mess.setText("Le document a été créé et sauvegardé avec succès.");
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        download.setOpacity(1);
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
        arbeiter.setItems(Database.getInstance().getVendor());
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

    public void setCon(LoginFXMLController Lcon) {
        this.Lcon = Lcon;
    }

    public boolean isOne(boolean one) {
        return one;
    }

    @FXML
    private void addMitarbeiter(MouseEvent event) throws IOException {
        Stage stage = Französisch.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("addMitarbeiter.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    


}
