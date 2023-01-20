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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.util.Duration;

public class AddBalance implements Initializable {

    ConnectClass conclass = new ConnectClass();
    Connection connection = conclass.connectDb();

    @FXML
    private ChoiceBox<String> selectsim;
    String arr[] = {"Mastercard", "VISA"};
    @FXML
    private Label Addbalance;

    @FXML
    private TextField inputnumber;

    @FXML
    private Label Givennum;

    @FXML
    private Label Paymentmethod;

    @FXML
    private TextField amount;

    @FXML
    private ImageView backImage;

    @FXML
    private PasswordField Password;

    @FXML
    private Button btnSubmit;

    @FXML
    private Button btnBack;

    @FXML
    private Label lblWarning;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        selectsim.getItems().addAll(arr);

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
    
    @FXML
    void subInfo(ActionEvent event) throws Exception {
        

        if (inputnumber.getText().equals("") || amount.getText().equals("") || selectsim.getValue().equals("") || Password.getText().equals("")) {
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
            visiblePause.setOnFinished(event1 -> lblWarning.setText(""));
            visiblePause.play();
            lblWarning.setText("Please enter all the fields");
        } else {
            String choice = selectsim.getValue();
        String number = inputnumber.getText();
        int money = Integer.parseInt(amount.getText());

        long millis = System.currentTimeMillis();
        java.sql.Date date = new java.sql.Date(millis);

            if (Login.thePass.equals(Password.getText())) {
                try {

                    PreparedStatement statement = connection.prepareStatement("INSERT INTO `addbal` VALUES (?, ?, ?, ?)");
                    statement.setString(1, number);
                    statement.setInt(2, money);
                    statement.setDate(3, date);
                    statement.setString(4, choice);
                    statement.execute();

                    PreparedStatement statement2 = connection.prepareStatement("SELECT Amount FROM Users WHERE Username=?");
                    statement2.setString(1, Login.theUser);
                    ResultSet results;
                    results = statement2.executeQuery();

                    results.next();
                    int Cbal = Integer.parseInt(results.getString("Amount"));
                    Cbal = Cbal + money;
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
}
