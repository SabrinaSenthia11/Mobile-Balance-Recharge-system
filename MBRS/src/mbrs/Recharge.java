/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mbrs;

import Connectivity.ConnectClass;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author Hp
 */
public class Recharge implements Initializable {

    ConnectClass conclass = new ConnectClass();
    Connection connection = conclass.connectDb();

    @FXML
    private ChoiceBox<String> chcType;
    String checktype[] = {"Prepaid", "Postpaid"};

    @FXML
    private Label lblType;

    @FXML
    private ChoiceBox<String> chcMobileOperator;
    String mobileOperator[] = {"GP", "Banglalink", "Robi", "Airtel", "Teletalk"};

    @FXML
    private Label lblContact;

    @FXML
    private Button btmBack;

    @FXML
    private TextField txtContNum;

    @FXML
    private Label lblMobileOperator;

    @FXML
    private TextField txtAmount;

    @FXML
    private Label lblAmount;

    @FXML
    private Button btmSend;

    @FXML
    private ImageView backImage;

    @FXML
    private Label lblPass;

    @FXML
    private PasswordField Password;

    @FXML
    private Label lblWarning;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        chcType.getItems().addAll(checktype);
        chcMobileOperator.getItems().addAll(mobileOperator);

    }

    @FXML
    void loadMoney(ActionEvent event) {
        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

        if (txtContNum.getText().equals("") || txtAmount.getText().equals("") || chcType.getValue().equals("") || chcMobileOperator.getValue().equals("") || Password.getText().equals("")) {
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
            visiblePause.setOnFinished(event1 -> lblWarning.setText(""));
            visiblePause.play();
            lblWarning.setText("Please enter all the fields");
        } else {
            if (Login.thePass.equals(Password.getText())) {
                try {
                    String type = chcType.getValue();
                    String sim = chcMobileOperator.getValue();
                    int amount = Integer.parseInt(txtAmount.getText());
                    String num = txtContNum.getText();

                    PreparedStatement statement = connection.prepareStatement("INSERT INTO `recharge` (`Cnum`, `Amount`, `Type`, `Sim`, `Date`) VALUES (?, ?, ?, ?, ?)");
                    statement.setString(1, num);
                    statement.setInt(2, amount);
                    statement.setString(3, type);
                    statement.setString(4, sim);
                    statement.setDate(5, date);
                    statement.execute();

                    PreparedStatement statement2 = connection.prepareStatement("SELECT Amount FROM Users WHERE Username=?");
                    statement2.setString(1, Login.theUser);
                    ResultSet results;
                    results = statement2.executeQuery();

                    results.next();
                    int Cbal = Integer.parseInt(results.getString("Amount"));
                    Cbal = Cbal - amount;
                    PreparedStatement statement3 = connection.prepareStatement("UPDATE Users SET Amount=? WHERE Username=?");
                    statement3.setInt(1, Cbal);
                    statement3.setString(2, Login.theUser);
                    statement3.execute();

                } catch (SQLException sql) {
                    System.out.println(sql);
                }

            }
        }
    }

    @FXML
    void gotoHome(ActionEvent event) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("HomeFXML.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Home");
        stage.setScene(scene);
        stage.show();
    }

}
