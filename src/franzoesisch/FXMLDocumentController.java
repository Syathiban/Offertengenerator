/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package franzoesisch;

import java.util.Calendar;
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
    String payment1, payment2, payment3;

    private ObservableList<String> Zahlung = FXCollections.observableArrayList("PayPal.", "bank transfer.", "credit card.");
    private ObservableList<String> Zahlungen = FXCollections.observableArrayList("par PayPal.", "par virement bancaire.", "par carte de crédit.");

    @FXML
    private Button submit;
    @FXML
    private TextField amount;
    @FXML
    private TextArea letter;
    int resultat;
    private String offertenTextPDF = "";
    private double xOffset = 0;

    double mwst, res, bbb;
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
            Nachname = "<Nachname>", Adresszeile_1 = "<Adresse>", Ort = "<Stadt>", Anrede = "<Anrede>", automatischesDatum, Produktname, Termin1 = "<Termin1>", Ablaufdatum;

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
    private ImageView logout;
    @FXML
    private ImageView menuOpen;

    boolean activate = false;
    private Button english;
    private Button french;
    @FXML
    private Label title;
    String messa = "";
    private int plz, aaa;
    String street, ort;
    String comparison = "english";
    private boolean one = false;
    public static FXMLDocumentController instance;
    LoginFXMLController Lcon;
    @FXML
    private DatePicker dateEmpfangsdatum;

    String Aktionshinweis, Beraterhinweis, Verkäufer, Position;

    int Zahlungsfrist = 30;
    Double MWST = 7.7, Rabattmenge, bbs;
    @FXML
    private ImageView mitarbeiter;
    @FXML
    private ComboBox<String> arbeiter;
    @FXML
    private ImageView download;
    @FXML
    private Label mess;
    @FXML
    private Label clAdd;
    String warn;
    @FXML
    private ImageView clAddback;
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
    private Label logOut;
    private DatePicker ablaufdatum;

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
            Einzelpreis = Database.getInstance().getProdukt(Produktname).getDouble("stückpreis");
            
            if (!amount.getText().trim().isEmpty()) {
                aaa = Integer.parseInt(amount.getText());
            }

            

            if (dateEmpfangsdatum.getValue() != null) {
                LocalDate ld = dateEmpfangsdatum.getValue();
                Calendar c = Calendar.getInstance();
                c.set(ld.getYear(), ld.getMonthValue() - 1, ld.getDayOfMonth());
                Date date = c.getTime();
                DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");

                Termin1 = dateFormat.format(date);

                c.add(Calendar.DAY_OF_MONTH, 30);
                Date date2 = c.getTime();
                Ablaufdatum = dateFormat.format(date2);
            }
            Verkäufer = arbeiter.getSelectionModel().getSelectedItem();

        }

    }

    public void CreateOfferText() {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        Date date = new Date();
        automatischesDatum = dateFormat.format(date);
        bbb = 10;
        Totalpreis = Einzelpreis * aaa;
        res = Totalpreis / bbb;
        Rabattmenge = 4000.00;

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
                + " " + aaa + "      " + Produktname + "      " + Totalpreis + " CHF" + "      " + MWST + "%\n\n"
                + "Nous vous proposons le " + Produktname + " au prix de " + Einzelpreis + " CHF chacun, y compris " + MWST + "% de TVA .\n"
                + "En outre, vous pouvez profiter d’une remise spéciale de " + bbb + " % pour toute commande" + "  supérieure à " + Rabattmenge + " CHF.\n"
                + "Nous promettons de vous livrer la commande sous 7 jours." + "\n" + "Le délai de paiement est de " + Zahlungsfrist + " jours" + " " + "après réception de la marchandise." + "\n"
                + "Nous vous prions de payer avec" + payment + " Cette offre est valable" + " " + "jusqu'au " + Ablaufdatum + "." + "\n\n"
                + "Actuellement, nous avons notre mois de promotion pour nos produits.\nAvec nos produits, ils réalisent ce qu'ils ont manqué. C'est l'occasion idéale de trouver de nouveaux produits pour un public jeune et urbain.\n"
                + "Notre gamme de produits est en constante évolution.\nAppelez-nous si vous souhaitez qu'un membre de notre personnel vous présente nos nouveaux produits à la maison. \nOu visitez notre boutique à Lausanne pour découvrir nos derniers produits et services. \n"
                + "" + "Si vous avec encore des questions, n'hésitez pas de nous contacter.\n"
                + "Dans l’attente de votre commande, nous vous prions d'agréer " + Anrede + " nos meilleures salutations. \n\n"
                + Verkäufer);
        letter.setEditable(false);

    }

    public String getHTMLText() {

        String offertenTextPDF = "";

        offertenTextPDF = "  VIN de Lausanne SA <br>" + "   3, Rue de la Piquette <br>   2000 Lausanne<br><br>";
        offertenTextPDF += "" + Firmenname + "<br>" + Nachname + " " + Vorname + "<br>" + Adresszeile_1 + "<br>" + Postleitzahl + " " + Ort + "<br><br><br><br>";
        offertenTextPDF += "Lausanne, " + automatischesDatum + "<br><br>";
        offertenTextPDF += "Offre pour " + Produktname + "<br><br>";
        offertenTextPDF += "" + Anrede + " " + Nachname + " <br><br>";

        offertenTextPDF += " Nous avons bien reçu votre demande du" + " " + Termin1 + "  " + "et nous vous en remercions vivement." + "<br>";
        offertenTextPDF += "Nous avons le plaisir de soumettre l'offre suivante." + "<br><br>";

        offertenTextPDF += "" + aaa + "       " + Produktname + "       " + Totalpreis + " CHF" + "       " + MWST + "%" + "<br><br>";

        offertenTextPDF += "Nous vous proposons le " + Produktname + " au prix de " + Einzelpreis + " CHF chacun, y compris " + MWST + "% de TVA." + "<br>"
                + "En outre, vous pouvez profiter d’une remise spéciale de " + bbb + " % pour toute commande" + "<br>" + "supérieure à " + Rabattmenge + " CHF." + "<br><br>";

        offertenTextPDF += "Nous promettons de vous livrer la commande sous 7 jours." + "\n" + "  Le délai de paiement est de " + Zahlungsfrist + " jours" + " " + "après réception de la marchandise." + "\n"
                + "  Nous vous prions de payer " + payment + " Cette offre est valable" + " " + "jusqu'au " + Ablaufdatum + "." + "\n\n  ";

        offertenTextPDF += "Actuellement, nous avons notre mois de promotion pour nos produits." + "\n" + "Avec nos produits, ils réalisent ce qu'ils ont manqué. C'est l'occasion idéale de trouver de nouveaux produits pour un public jeune et urbain." + "<br><br>";
        offertenTextPDF += "\n" + "Notre gamme de produits est en constante évolution." + "\n" + " Appelez-nous si vous souhaitez qu'un membre de notre personnel vous présente nos nouveaux produits à la maison." + "\n" + " Ou visitez notre boutique à Lausanne pour découvrir nos derniers produits et services. \n";

        offertenTextPDF += "" + " Si vous avec encore des questions, n'hésitez pas de nous contacter." + "<br><br>";
        offertenTextPDF += "Dans l’attente de votre commande, nous vous prions d'agréer " + Anrede + " nos meilleures salutations." + "<br><br>";

        offertenTextPDF += Verkäufer;
        return offertenTextPDF;
    }

    public String getHTMLTextEnglish() {

        String offertenTextPDF = "";

        offertenTextPDF = "  VIN de Lausanne SA <br>" + "   3, Rue de la Piquette <br>   2000 Lausanne<br><br>";
        offertenTextPDF += "" + Firmenname + "<br>" + Nachname + " " + Vorname + "<br>" + Adresszeile_1 + "<br>" + Postleitzahl + " " + Ort + "<br><br><br><br>";
        offertenTextPDF += "Lausanne, " + automatischesDatum + "<br><br>";
        offertenTextPDF += "Offre pour " + Produktname + "<br><br>";
        offertenTextPDF += "" + Anrede + " " + Nachname + " <br><br>";
        offertenTextPDF += " We have received your request from" + " " + Termin1 + " " + "and we thank you very much." + "<br>";
        offertenTextPDF += "We are pleased to submit the following offer." + "<br><br>";
        offertenTextPDF += "" + aaa + "   " + Produktname + "   " + Totalpreis + " CHF" + "  " + MWST + "%" + "<br><br>";
        offertenTextPDF += "We offer you the " + Produktname + " at the price of " + Einzelpreis + " CHF each, including " + MWST + "% VAT" + "<br><br>";
        offertenTextPDF += "In addition, you can benefit from a special discount of " + bbb + " % for any order that is" + "<br>" + "higher than " + Rabattmenge + " CHF." + "<br><br>";
        offertenTextPDF += "We promise to deliver the order within 7 days." + "\n" + " The payment period is " + Zahlungsfrist + " days" + " " + "after receipt of goods." + "\n"
                + "  Please pay with" + payment + "This offer is valid until " + Ablaufdatum + "." + "\n\n  ";
        offertenTextPDF += "Right now, we have our product promotion month. With our products, you will realize what you missed." + "\n" + "It's the perfect opportunity to find new products for a young, urban audience." + "<br><br>";
        offertenTextPDF += "\n" + "Our product range is constantly evolving. Call us if you want a member of our staff to introduce you to our new products at home." + "\n" + "Or visit our shop in Lausanne to discover our latest products and services. \n";
        offertenTextPDF += "" + " If you still have questions, don't hesitate to contact us." + "<br><br>";
        offertenTextPDF += "While waiting for your order, we ask you to accept our best regards." + "<br><br>";
        offertenTextPDF += Verkäufer;
        return offertenTextPDF;
    }

    @FXML
    private void generatePDF(MouseEvent event) throws IOException, DocumentException {

        Document document = new Document(PageSize.A4);

        PdfWriter.getInstance(document, new FileOutputStream("Texte d'offre.pdf"));

        document.open();

        HTMLWorker hw = new HTMLWorker(document);
        hw.parse(new StringReader("<html><p>" + this.getHTMLText().concat(offertenTextPDF) + "</p></html>"));
        document.close();

        mess.setText(messa);
    }

    @FXML
    private void handleButtonAction(ActionEvent event) {
        if (product.getSelectionModel().isEmpty() || amount.getText().isEmpty() || zahlung.getSelectionModel().isEmpty() || Cliente.getSelectionModel().isEmpty()
                || arbeiter.getSelectionModel().isEmpty() || dateEmpfangsdatum.getValue() == null) {
            mess.setText(warn);
        } else {
            download.setOpacity(1);
            String nuts = Database.getInstance().getLanguages();
            if (nuts.equals(comparison)) {
                CreateOfferEnglish();
                letter.setText(offertenText);
            } else {
                CreateOfferText();
                letter.setText(offertenText);
            }
        }

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
        arbeiter.setItems(Database.getInstance().getVendor());
        String language = Database.getInstance().getLanguages();
        System.out.println(language + comparison);
        if (language.equals(comparison)) {
            System.out.println("hallo");
            title.setText("Create Offer");
            product.setPromptText("Product:");
            amount.setPromptText("Amount:");
            zahlung.setPromptText("Payment method");
            Cliente.setPromptText("Client");
            arbeiter.setPromptText("Employee");
            dateEmpfangsdatum.setPromptText("Date");
            submit.setText("Submit");
            messa = "The document was successfully created and saved.";
            clAdd.setText("Add clients");
            plAdd.setText("Add products");
            logOut.setText("Log out");
            arAdd.setText("Add Employee");
            zahlung.setItems(Zahlung);
            warn = "All fields must be filled in.";
        } else {
            warn = "Tous les champs doivent être remplis.";
            messa = "Le document a été créé et sauvegardé avec succès.";
            zahlung.setItems(Zahlungen);
        }
        CreateOfferText();

    }

    public void CreateOfferEnglish() {
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.YYYY");
        Date date = new Date();
        automatischesDatum = dateFormat.format(date);
        Totalpreis = Einzelpreis * aaa;
        bbb = 10;
        res = Totalpreis / bbb;
        Rabattmenge = 4000.00;

        item = product.getSelectionModel().getSelectedItem();
        payment = zahlung.getSelectionModel().getSelectedItem();
        offertenText = ("VIN de Lausanne SA \n" + "3, Rue de la Piquette \n 2000 Lausanne\n\n"
                + Firmenname + "\n" + Nachname + " " + Vorname + "\n" + Adresszeile_1 + "\n" + Postleitzahl + " " + Ort + "\n\n\n\n"
                + "Lausanne, " + automatischesDatum + "\n\n\n"
                + "Offer for " + Produktname + "\n"
                + "\n\n"
                + "\n"
                + "" + Anrede + " " + Nachname + ",\n\n"
                + "We have received your request from" + "" + Termin1 + "" + "and we thank you very much." + "\n"
                + "We are pleased to submit the following offer." + "\n\n"
                + "" + aaa + "  " + Produktname + "  " + Totalpreis + " CHF " + " " + MWST + "%\n\n"
                + "We offer you the " + Produktname + " at the price of " + Einzelpreis + " CHF each, including " + MWST + "% VAT .\n"
                + "In addition, you can benefit from a special discount of " + bbb + " % for any order that is higher than " + Rabattmenge + " CHF.\n"
                + "We promise to deliver the order within 7 days." + " The payment period is " + Zahlungsfrist + "days" + "" + "after receipt of goods." + "\n"
                + "Please pay with" + payment + "This offer is valid until " + Ablaufdatum + "." + "\n\n"
                + "Right now, we have our product promotion month. With our products, you will realize what you missed." + "\n" + "It's the perfect opportunity to find new products for a young, urban audience."
                + "Our product range is constantly evolving. Call us if you want a member of our staff to introduce you to our new products at home." + "\n" + "Or visit our shop in Lausanne to discover our latest products and services. \n"
                + "If you still have questions, don't hesitate to contact us."
                + "While waiting for your order, we ask you to accept our best regards. \n\n\n"
                + Verkäufer);
        letter.setEditable(false);
    }

    @FXML
    private void close(ActionEvent event) {
        Database.getInstance().editLanguage();
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
            logout.setOpacity(0);
            mitarbeiter.setOpacity(0);
            activated = false;
        } else {
            menu.setOpacity(1);
            clientAdd.setOpacity(1);
            productAdd.setOpacity(1);
            logout.setOpacity(1);
            mitarbeiter.setOpacity(1);
            activated = true;
        }
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
        logOut.setOpacity(0);
    }

    @FXML
    private void logOutEnter(MouseEvent event) {
        if (activated == true) {
            logOutBack.setOpacity(1);
            logOut.setOpacity(1);
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
