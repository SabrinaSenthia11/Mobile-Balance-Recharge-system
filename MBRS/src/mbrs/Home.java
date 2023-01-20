package mbrs;

import Connectivity.ConnectClass;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Home {
    
    ConnectClass conclass = new ConnectClass();
        Connection connection = conclass.connectDb();
    
    @FXML
    private Button btnBack;
    
    @FXML
    private Button btnCheckBal;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnTransaction;

    @FXML
    private Label lblCheckBal;

    @FXML
    private Button btnAddBal;

    @FXML
    private Button btnRecharge;
    
    

    @FXML
    void showBalance(ActionEvent event) {
        
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT Amount FROM Users WHERE Username=?");
            statement.setString(1, Login.theUser);
            ResultSet results;
            results = statement.executeQuery();
            
            results.next();
            String amount = results.getString("Amount");
            PauseTransition visiblePause = new PauseTransition(Duration.seconds(3));
                visiblePause.setOnFinished(event1 -> btnCheckBal.setText("Check Balance"));
                visiblePause.play();
            btnCheckBal.setText(amount);
            
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    @FXML
    void gotoRecharge(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("RechargeFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Recharge To Sim");
        stage.setScene(scene);
        stage.show();
    }
    

    @FXML
    void gotoAddBalance(ActionEvent event) throws Exception{
     Parent root = FXMLLoader.load(getClass().getResource("AddBalanceFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Add Balance");
        stage.setScene(scene);
        stage.show();   
    }

    @FXML
    void gotoLogin(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("LoginFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    void gotoTransaction(ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("TransactionFXML.fxml"));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setTitle("Transaction");
        stage.setScene(scene);
        stage.show();
    }

}
